package com.example.ShopCMSBlog.repositories;
import com.example.ShopCMSBlog.entites.UserRoleEntity;
import com.example.ShopCMSBlog.entites.UserRoleId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CommonRepository<UserRoleEntity, UserRoleId> {

    @Query("SELECT ur FROM UserRoleEntity ur WHERE ur.id.userId = :userId")
    List<UserRoleEntity> findAllByUserId(@Param("userId") Long userId);
    @Query("DELETE FROM UserRoleEntity ur WHERE ur.id.userId IN :userIds")
    @Modifying
    void deleteByUserIdIn(@Param("userIds") List<Long> userIds);
}
