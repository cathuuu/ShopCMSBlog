package com.example.ShopCMSBlog.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDto {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

}
