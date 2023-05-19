package com.pcs.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcs.user.dto.NewPasswordRequiredRequest;
import com.pcs.user.dto.SignInRequest;
import com.pcs.user.dto.SignInResponse;
import com.pcs.user.dto.UserDto;
import com.pcs.user.model.User;
import com.pcs.user.service.UserService;

@RestController
@RequestMapping(path = "/api/v1/pcs")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto createUserRequest) {
        User user = modelMapper.map(createUserRequest, User.class);
        User createdUser = userService.createUser(user);
        UserDto createUserResponse = modelMapper.map(createdUser, UserDto.class);
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return new ResponseEntity<>(userService.signin(signInRequest), HttpStatus.OK);
    }

    @PostMapping(path = "/newPasswordRequired")
    public ResponseEntity<SignInResponse> newPasswordRequired(@RequestBody NewPasswordRequiredRequest request) {
        SignInResponse signInResponse = userService.newPasswordRequired(request);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/update-user/{employeeId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer employeeId, @RequestBody UserDto updateUserRequest) {
        User user = modelMapper.map(updateUserRequest, User.class);
        User updatedUser = userService.updateUser(employeeId, user);
        UserDto updateUserResponse = modelMapper.map(updatedUser, UserDto.class);
        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> getUsersList() {
        List<UserDto> users = userService.getUsersList().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
