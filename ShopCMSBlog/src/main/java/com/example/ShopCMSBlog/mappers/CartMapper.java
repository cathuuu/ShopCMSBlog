package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.CartEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartMapper {

    public static CartDto toDto(CartEntity entity) {
        CartDto dto = new CartDto();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static CartEntity toEntity(CartDto dto) {
        CartEntity entity = new CartEntity();
        entity.setId(dto.getId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
    public static List<CartDto> toDtoList (List<CartEntity> entityList) {
        return entityList.stream().map(CartMapper::toDto).collect(Collectors.toList());
    }
}
