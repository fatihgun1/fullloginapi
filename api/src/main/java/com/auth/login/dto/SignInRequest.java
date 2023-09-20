package com.auth.login.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
