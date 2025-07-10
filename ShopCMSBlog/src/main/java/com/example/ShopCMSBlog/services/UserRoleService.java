package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.entites.UserRoleEntity;

import java.util.List;


public interface UserRoleService extends CommonService<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserId(Long userId);
    void deleteByUserIdIn(List<Long> userIds);
}
