package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<UserEntity, Long> {
    List<UserEntity> findByIdAndUsername(Long id, String username);

    Optional<UserEntity> findByUsername(String username);
}
