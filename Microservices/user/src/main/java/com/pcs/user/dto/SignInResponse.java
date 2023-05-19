package com.pcs.user.dto;

import lombok.Data;

@Data
public class SignInResponse {
    private boolean firstSignIn;
    private String session;
    private UserDto user;
    private String accessToken;
}
