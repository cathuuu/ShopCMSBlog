package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.entites.UserRoleEntity;
import com.example.ShopCMSBlog.entites.UserRoleId;

import java.util.List;


public interface UserRoleService extends CommonService<UserRoleEntity, UserRoleId> {

    List<UserRoleEntity> findAllByUserId(Long userId);
    void deleteByUserIdIn(List<Long> userIds);
}
