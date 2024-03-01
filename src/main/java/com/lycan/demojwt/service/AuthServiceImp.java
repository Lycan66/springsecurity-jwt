package com.lycan.demojwt.service;

import com.lycan.demojwt.dto.AuthResponse;
import com.lycan.demojwt.dto.LoginRequest;
import com.lycan.demojwt.dto.RegisterRequest;
import com.lycan.demojwt.model.User;
import com.lycan.demojwt.repository.UserRepository;
import com.lycan.demojwt.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest credentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                                                                                   credentials.getPassword()));
        
        UserDetails user = userRepo.findByUsername(credentials.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest userData) {
        User user = User.builder()
                .username(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .firstname(userData.getFirstname())
                .lastname(userData.getLastname())
                .country(userData.getCountry())
                .rol(Rol.USER)
                .build();
        
        userRepo.save(user);
        
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
    
}
