package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.CategoryEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CategoryMapper {
    public static CategoryDto toDto(CategoryEntity entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }
    public static CategoryEntity toEntity(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
    public static List<CategoryDto> toDtoList (List<CategoryEntity> entityList) {
        return entityList.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }
}
