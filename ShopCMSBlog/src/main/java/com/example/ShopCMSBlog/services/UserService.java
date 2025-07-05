package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.entites.UserEntity;

import java.util.List;

public interface UserService extends CommonService<UserEntity, Long>{
    List<UserDto> findByIdAndUsername(Long id, String username);
    UserDto save(UserRequestDto userRequestDtoDto);
    void delete(Long id);
}
