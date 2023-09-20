package com.auth.login.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
