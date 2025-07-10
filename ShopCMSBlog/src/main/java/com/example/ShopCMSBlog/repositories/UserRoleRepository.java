package com.example.ShopCMSBlog.repositories;
import com.example.ShopCMSBlog.entites.UserRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CommonRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserId(Long userId);
    void deleteByUserIdIn(List<Long> userIds);
}
