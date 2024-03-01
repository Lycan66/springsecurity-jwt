package com.lycan.demojwt.service;

import com.lycan.demojwt.dto.AuthResponse;
import com.lycan.demojwt.dto.LoginRequest;
import com.lycan.demojwt.dto.RegisterRequest;

public interface AuthService {
    public AuthResponse login(LoginRequest credentials);
    public AuthResponse register(RegisterRequest userData);
}
