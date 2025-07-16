package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService extends CommonService<UserEntity, Long>{
    List<UserDto> findByIdAndUsername(Long id, String username);
    UserDto save(UserRequestDto userRequestDtoDto);
    void delete(Long id);
    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> getAllUsers(Pageable pageable);

    Page<UserEntity> searchUsersWithPagination(String username, String email, Long userId, Pageable pageable);

    Optional<UserEntity> getUserByEmail(String email);

    Optional<UserEntity> getUserByUsernameJpa(String username);

    Optional<UserEntity> getUserById(Long id);
}
