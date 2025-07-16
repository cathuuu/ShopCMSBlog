package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    Page<UserEntity> findAllUsersPagedNative(Pageable pageable);
    Page<UserEntity> findUsersByUsernameContainingPagedNative(String username, Pageable pageable);
    Page<UserEntity> findUsersByCriteriaPaginatedNative(String username, String email, Long userId, Pageable pageable);

    // Phương thức lấy danh sách không phân trang (sẽ dùng findList của BaseNativeQuery)
    List<UserEntity> findAllActiveUsersNative();

    // Phương thức lấy một đối tượng duy nhất (sẽ dùng findOne của BaseNativeQuery)
    Optional<UserEntity> findByIdNative(Long id);
    Optional<UserEntity> findByEmailNative(String email);
}
