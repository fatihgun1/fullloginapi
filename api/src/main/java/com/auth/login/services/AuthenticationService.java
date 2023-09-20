package com.auth.login.services;

import com.auth.login.dto.JwtAuthenticationResponse;
import com.auth.login.dto.RefreshTokenRequest;
import com.auth.login.dto.SignInRequest;
import com.auth.login.dto.SignupRequest;
import com.auth.login.model.UserModel;

public interface AuthenticationService {
    UserModel signUp(SignupRequest signupRequest);
    JwtAuthenticationResponse signIn(SignInRequest signupRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
