package com.pcs.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.pcs.user.dao.UserRepository;
import com.pcs.user.dto.NewPasswordRequiredRequest;
import com.pcs.user.dto.SignInRequest;
import com.pcs.user.dto.SignInResponse;
import com.pcs.user.dto.UserDto;
import com.pcs.user.exception.CustomException;
import com.pcs.user.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Autowired
    private ModelMapper modelMapper;

    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;

    /**
     * This is creating a new user.
     * 
     * @param user User.
     * 
     * @return user User.
     */
    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new CustomException("Email already used");
        this.createCognitoUser(user.getEmail(), "Pass@123");
        return userRepository.save(user);
    }

    /**
     * This is returning all the users present.
     * 
     * @return users List.
     */
    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    /**
     * This is updating an user already present.
     * 
     * @param user User.
     * 
     * @return user User.
     */
    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User don't exist"));
        if (!existingUser.getEmail().equals(user.getEmail()))
            throw new CustomException("Email cannot be updated");
        user.setEmployeeId(existingUser.getEmployeeId());
        return userRepository.save(user);
    }

    /**
     * This is creating a new user in aws cognito.
     * 
     * @param username String.
     * 
     * @param password String.
     */
    private void createCognitoUser(String username, String password) {
        try {
            AdminCreateUserRequest userRequest = new AdminCreateUserRequest().withUserPoolId(this.userPoolId)
                    .withUsername(username)
                    .withTemporaryPassword(password);
            cognitoClient.adminCreateUser(userRequest);
        } catch (AWSCognitoIdentityProviderException e) {
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This is signing in an user.
     * 
     * @param signInRequest SignInRequest
     * 
     * @return signInResponse SignInResponse
     */
    @Override
    public SignInResponse signin(SignInRequest signInRequest) {
        SignInResponse signInResponse = new SignInResponse();
        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", signInRequest.getEmail());
        authParams.put("PASSWORD", signInRequest.getPassword());
        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId)
                .withUserPoolId(userPoolId).withAuthParameters(authParams);
        try {
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);
            signInResponse.setSession(result.getSession());
            if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {
                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                    signInResponse.setFirstSignIn(true);
                } else {
                    throw new CustomException(
                            "User has other challenge " + result.getChallengeName());
                }
            } else {
                AuthenticationResultType authenticationResult = result.getAuthenticationResult();
                User user = userRepository.findByEmail(signInRequest.getEmail());
                signInResponse.setAccessToken(authenticationResult.getAccessToken());
                signInResponse.setUser(modelMapper.map(user, UserDto.class));
            }
        } catch (InvalidParameterException | NotAuthorizedException e) {
            throw new CustomException(e.getMessage());
        } 
        return signInResponse;
    }

    /**
     * This is for first time signed in user who need to change password.
     * 
     * @param signInRequest SignInRequest
     * 
     * @return signInResponse SignInResponse
     */
    @Override
    public SignInResponse newPasswordRequired(NewPasswordRequiredRequest passwordRequiredRequest) {
        SignInResponse signInResponse = new SignInResponse();
        final Map<String, String> challengeResponses = new HashMap<>();
        challengeResponses.put("USERNAME", passwordRequiredRequest.getEmail());
        challengeResponses.put("PASSWORD", passwordRequiredRequest.getOldPassword());
        challengeResponses.put("NEW_PASSWORD", passwordRequiredRequest.getNewPassword());
        final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
                .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .withClientId(clientId).withUserPoolId(userPoolId)
                .withChallengeResponses(challengeResponses)
                .withSession(passwordRequiredRequest.getSession());
        signInResponse.setSession(passwordRequiredRequest.getSession());
        try {
            AdminRespondToAuthChallengeResult resultChallenge = cognitoClient.adminRespondToAuthChallenge(request);
            AuthenticationResultType authenticationResult = resultChallenge.getAuthenticationResult();
            User user = userRepository.findByEmail(passwordRequiredRequest.getEmail());
            signInResponse.setAccessToken(authenticationResult.getAccessToken());
            signInResponse.setUser(modelMapper.map(user, UserDto.class));
        } catch (InvalidParameterException | NotAuthorizedException e) {
            throw new CustomException(e.getMessage());
        }
        return signInResponse;
    }

}
