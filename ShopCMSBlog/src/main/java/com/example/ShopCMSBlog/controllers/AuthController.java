package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.LoginDto;
import com.example.ShopCMSBlog.services.AuthService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlUtils.AUTH_URL)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto authRequest) {
        String token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
}
