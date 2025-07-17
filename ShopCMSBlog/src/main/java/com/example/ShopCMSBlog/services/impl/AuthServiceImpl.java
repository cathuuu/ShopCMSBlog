package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.LoginDto;
import com.example.ShopCMSBlog.dtos.RegisterDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.securities.JwtTokenComponent;
import com.example.ShopCMSBlog.services.AuthService;
import com.example.ShopCMSBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenComponent jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    public String login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        return jwtUtil.generateAccessToken(loginDto.getUsername());
    }

    @Override
    public String register(RegisterDto registerDto) throws RoleNotFoundException {
        UserEntity newUser = userDetailServiceImpl.registerNewUser(registerDto);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDto.getUsername(), registerDto.getPassword())
        );
        return jwtUtil.generateAccessToken(registerDto.getUsername());
    }
}

