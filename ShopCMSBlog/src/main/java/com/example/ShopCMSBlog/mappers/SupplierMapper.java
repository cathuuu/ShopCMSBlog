package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {
    public static SupplierDto toDto(SupplierEntity entity) {
        SupplierDto dto = new SupplierDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContactName(entity.getContactName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        return dto;
    }
    public static SupplierEntity toEntity(SupplierDto dto) {
        SupplierEntity entity = new SupplierEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setContactName(dto.getContactName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        return entity;
    }
    public static List<SupplierDto> toDtoList (List<SupplierEntity> entityList) {
        return entityList.stream().map(SupplierMapper::toDto).collect(Collectors.toList());
    }
}
