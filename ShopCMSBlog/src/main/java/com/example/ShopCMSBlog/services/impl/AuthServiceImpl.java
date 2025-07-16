package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.LoginDto;
import com.example.ShopCMSBlog.securities.JwtTokenComponent;
import com.example.ShopCMSBlog.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenComponent jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        return jwtUtil.generateAccessToken(loginDto.getUsername());
    }
}

