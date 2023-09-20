package com.auth.login.services.impl;

import com.auth.login.dto.JwtAuthenticationResponse;
import com.auth.login.dto.RefreshTokenRequest;
import com.auth.login.dto.SignInRequest;
import com.auth.login.dto.SignupRequest;
import com.auth.login.model.RoleEnum;
import com.auth.login.model.UserModel;
import com.auth.login.repository.UserRepository;
import com.auth.login.services.AuthenticationService;
import com.auth.login.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserModel signUp(SignupRequest signupRequest){
        UserModel user = new UserModel();
        user.setEmail(signupRequest.getEmail());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setRoles(RoleEnum.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return user;
    }


    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
         authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt =jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return response;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        UserModel user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            return response;
        }
        return null;
    }
}
