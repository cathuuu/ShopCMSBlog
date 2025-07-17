package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.enums.RoleEnum;
import com.example.ShopCMSBlog.services.impl.CommonServiceImpl;

import java.util.List;
import java.util.Optional;


public interface RoleService extends CommonService<RoleEntity, Long> {
    List<RoleDto> findByIdAndName(Long id, RoleEnum name);
    RoleDto save(RoleDto roleDto);
    RoleDto delete(Long id);
    Optional<RoleEntity> findByName(RoleEnum  name);
    List<RoleEntity> findAllByIdIn(List<Long> roleIds);
}
