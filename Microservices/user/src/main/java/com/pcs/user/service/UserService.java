package com.pcs.user.service;

import java.util.List;

import com.pcs.user.dto.NewPasswordRequiredRequest;
import com.pcs.user.dto.SignInRequest;
import com.pcs.user.dto.SignInResponse;
import com.pcs.user.model.User;

public interface UserService {
    
    public User createUser(User user);
    public SignInResponse signin(SignInRequest signInRequest);
    public SignInResponse newPasswordRequired(NewPasswordRequiredRequest newPasswordRequiredRequest);
    public User updateUser(Integer employeeId, User user);
    public List<User> getUsersList();

}
