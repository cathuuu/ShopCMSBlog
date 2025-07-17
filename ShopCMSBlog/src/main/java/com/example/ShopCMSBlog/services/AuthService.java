package com.example.ShopCMSBlog.services;


import com.example.ShopCMSBlog.dtos.LoginDto;
import com.example.ShopCMSBlog.dtos.RegisterDto;

import javax.management.relation.RoleNotFoundException;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto) throws RoleNotFoundException;

}
