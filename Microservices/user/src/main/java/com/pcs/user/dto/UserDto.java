package com.pcs.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private int employeeId;
    private String email;
    private String firstName;
    private String lastName;
    private String location;
    private String jobTitle;
    private String department;
    private String role;
}
