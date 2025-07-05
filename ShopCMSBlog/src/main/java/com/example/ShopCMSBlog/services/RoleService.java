package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.services.impl.CommonServiceImpl;

import java.util.List;


public interface RoleService extends CommonService<RoleEntity, Long> {
    List<RoleDto> findByIdAndName(Long id, String name);
    RoleDto save(RoleDto roleDto);
    RoleDto delete(Long id);
}
