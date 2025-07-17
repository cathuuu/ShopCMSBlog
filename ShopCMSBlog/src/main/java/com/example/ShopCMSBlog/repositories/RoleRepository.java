package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.enums.RoleEnum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CommonRepository<RoleEntity, Long>{
    List<RoleDto> findByIdAndName(Long id, RoleEnum name);

    Optional<RoleEntity> findByName(RoleEnum  name);
}
