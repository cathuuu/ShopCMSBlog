package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "user_roles")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleEntity {
    @EmbeddedId
    private UserRoleId id; // Sử dụng lớp khóa chính tổng hợp

    @ManyToOne
    @MapsId("userId") // Ánh xạ user_id từ UserRoleId
    @JoinColumn(name = "user_id") // Cột khóa ngoại trong bảng user_roles
    private UserEntity user; // Entity User của bạn

    @ManyToOne
    @MapsId("roleId") // Ánh xạ role_id từ UserRoleId
    @JoinColumn(name = "role_id") // Cột khóa ngoại trong bảng user_roles
    private RoleEntity role; // Entity Role của bạn
}
