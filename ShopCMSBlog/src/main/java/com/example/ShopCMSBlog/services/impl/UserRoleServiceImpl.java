package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.entites.UserRoleEntity;
import com.example.ShopCMSBlog.entites.UserRoleId;
import com.example.ShopCMSBlog.repositories.UserRoleRepository;
import com.example.ShopCMSBlog.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends CommonServiceImpl<UserRoleEntity, UserRoleId, UserRoleRepository> implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    protected UserRoleServiceImpl(UserRoleRepository repo) {
        super(repo);
    }

    @Override
    public List<UserRoleEntity> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void deleteByUserIdIn(List<Long> userIds) {
        repository.deleteByUserIdIn(userIds);
    }

}
