package com.auth.login.controller;

import com.auth.login.dto.JwtAuthenticationResponse;
import com.auth.login.dto.RefreshTokenRequest;
import com.auth.login.dto.SignInRequest;
import com.auth.login.dto.SignupRequest;
import com.auth.login.model.UserModel;
import com.auth.login.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserModel> signup(@RequestBody SignupRequest signupRequest){
    return ResponseEntity.ok(authenticationService.signUp(signupRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
