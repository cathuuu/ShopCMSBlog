package com.example.ShopCMSBlog.services;


import com.example.ShopCMSBlog.dtos.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);

}
