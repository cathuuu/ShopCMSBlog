package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.repositories.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<UserEntity> findAllUsersPagedNative(Pageable pageable) {
        String sql = "SELECT u.id, u.username, u.email, u.password FROM users u ORDER BY u.id ASC";
        String countSql = "SELECT COUNT(*) FROM users";
        return baseNativeQuery.findPage(sql, countSql, pageable, UserEntity.class);
    }

    @Override
    public Page<UserEntity> findUsersByUsernameContainingPagedNative(String username, Pageable pageable) {
        String sql = "SELECT u.id, u.username, u.email, u.password FROM users u WHERE u.username LIKE :username ORDER BY u.id ASC";
        String countSql = "SELECT COUNT(*) FROM users u WHERE u.username LIKE :username";
        Map<String, Object> params = Map.of("username", "%" + username + "%");
        return baseNativeQuery.findPage(sql, countSql, pageable, UserEntity.class, params);
    }

    @Override
    public Page<UserEntity> findUsersByCriteriaPaginatedNative(String username, String email, Long userId, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT u.id, u.username, u.email, u.password FROM users u WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM users u WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (username != null && !username.isEmpty()) {
            sqlBuilder.append(" AND u.username LIKE :username");
            countSqlBuilder.append(" AND u.username LIKE :username");
            params.put("username", "%" + username + "%");
        }
        if (email != null && !email.isEmpty()) {
            sqlBuilder.append(" AND u.email LIKE :email");
            countSqlBuilder.append(" AND u.email LIKE :email");
            params.put("email", "%" + email + "%");
        }
        if (userId != null) {
            sqlBuilder.append(" AND u.id = :userId");
            countSqlBuilder.append(" AND u.id = :userId");
            params.put("userId", userId);
        }

        if (pageable.getSort().isSorted()) {
            sqlBuilder.append(" ORDER BY ");
            pageable.getSort().forEach(order -> {
                sqlBuilder.append(order.getProperty()).append(" ").append(order.getDirection()).append(", ");
            });
            sqlBuilder.setLength(sqlBuilder.length() - 2);
        } else {
            sqlBuilder.append(" ORDER BY u.id ASC");
        }

        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, UserEntity.class, params);
    }

    @Override
    public List<UserEntity> findAllActiveUsersNative() {
        String sql = "SELECT u.id, u.username, u.email, u.password FROM users u WHERE u.status = 'ACTIVE'";
        return baseNativeQuery.findList(sql, UserEntity.class); // <-- Dùng findList
    }

    @Override
    public Optional<UserEntity> findByIdNative(Long id) {
        String sql = "SELECT u.id, u.username, u.email, u.password FROM users u WHERE u.id = ?";
        UserEntity user = baseNativeQuery.findOne(sql, UserEntity.class, id); // <-- Dùng findOne
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<UserEntity> findByEmailNative(String email) {
        String sql = "SELECT u.id, u.username, u.email, u.password FROM users u WHERE u.email = :email";
        Map<String, Object> params = Map.of("email", email);
        UserEntity user = baseNativeQuery.findOne(sql, UserEntity.class, params); // <-- Dùng findOne với named params
        return Optional.ofNullable(user);
    }
}
