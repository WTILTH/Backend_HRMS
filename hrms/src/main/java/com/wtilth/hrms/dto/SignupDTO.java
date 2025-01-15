package com.wtilth.hrms.dto;

import lombok.Data;

@Data
public class SignupDTO {

    private String username;
    private String password;
    private String role; // 'USER', 'ADMIN', 'DEVELOPER'

    // Getters and Setters
}
