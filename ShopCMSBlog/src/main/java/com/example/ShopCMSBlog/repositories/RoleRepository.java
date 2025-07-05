package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CommonRepository<RoleEntity, Long>{
    List<RoleDto> findByIdAndName(Long id, String name);
}
