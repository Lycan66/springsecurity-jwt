package com.lycan.demojwt.controllers;

import com.lycan.demojwt.dto.AuthResponse;
import com.lycan.demojwt.dto.LoginRequest;
import com.lycan.demojwt.dto.RegisterRequest;
import com.lycan.demojwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest credentials){
        return ResponseEntity.ok(authService.login(credentials));
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest userData){
        return ResponseEntity.ok(authService.register(userData));
    }
}
