package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.entites.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String username;
    String email;
    Set<RoleEntity> roles;


}
