package com.pcs.user.dto;

import lombok.Data;

@Data
public class NewPasswordRequiredRequest {

    private String email;
    private String oldPassword;
    private String newPassword;
    private String session;
    
}
