package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.entites.UserRoleEntity;
import com.example.ShopCMSBlog.securities.CustomUserDetail;
import com.example.ShopCMSBlog.services.RoleService;
import com.example.ShopCMSBlog.services.UserRoleService;
import com.example.ShopCMSBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    @Lazy
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        List<UserRoleEntity> userRoles = userRoleService.findAllByUserId(user.getId());
        if(userRoles.isEmpty()){
            return CustomUserDetail.build(user, new ArrayList<>());
        }
        List<Long> roleIds = userRoles.stream().map(userRole -> userRole.getId().getRoleId()).toList();
        List<RoleEntity> roles = roleService.findAllByIdIn(roleIds);
        return CustomUserDetail.build(user,roles);
    }
}
