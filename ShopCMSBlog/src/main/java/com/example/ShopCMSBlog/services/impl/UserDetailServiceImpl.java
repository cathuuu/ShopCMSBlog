package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.RegisterDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.entites.UserRoleEntity;
import com.example.ShopCMSBlog.entites.UserRoleId;
import com.example.ShopCMSBlog.enums.RoleEnum;
import com.example.ShopCMSBlog.exceptions.AppException;
import com.example.ShopCMSBlog.securities.CustomUserDetail;
import com.example.ShopCMSBlog.services.RoleService;
import com.example.ShopCMSBlog.services.UserRoleService;
import com.example.ShopCMSBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
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
    @Transactional
    public UserEntity registerNewUser(RegisterDto registerDto) throws RoleNotFoundException {
        if (userService.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new AppException("The username already exists!");
        }

        if (userService.getUserByEmail(registerDto.getEmail()).isPresent()) {
            throw new AppException("Email already exists!");
        }

        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setEmail(registerDto.getEmail());
        newUser = userService.save(newUser);

        RoleEntity defaultRole = roleService.findByName(RoleEnum.CUSTOMER)
                .orElseThrow(() -> new RoleNotFoundException("Default role not found."));

        UserRoleId userRoleId = new UserRoleId(newUser.getId(), defaultRole.getId());
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setId(userRoleId);
        userRole.setUser(newUser);
        userRole.setRole(defaultRole);

        userRoleService.save(userRole);

        return newUser;
    }

}
