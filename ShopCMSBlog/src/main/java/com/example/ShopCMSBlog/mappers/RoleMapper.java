package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.enums.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public static RoleDto toDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    public static RoleEntity toEntity(RoleDto dto) {
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
    public static List<RoleDto> toDtoList (List<RoleEntity> entityList) {
        return entityList.stream().map(RoleMapper::toDto).collect(Collectors.toList());
    }
}
