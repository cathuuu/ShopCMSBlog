package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartItemMapper {
    public static CartItemDto toDto(CartItemEntity entity) {
        CartItemDto dto = new CartItemDto();
        dto.setId(entity.getId());
        dto.setCart(CartMapper.toDto(entity.getCart()));
        dto.setProduct(ProductMapper.toDto(entity.getProduct()));
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setAddedAt(entity.getAddedAt());
        return dto;
    }
    public static CartItemEntity toEntity(CartItemDto dto) {
        CartItemEntity entity = new CartItemEntity();
        entity.setId(dto.getId());
        entity.setCart(CartMapper.toEntity(dto.getCart()));
        entity.setProduct(ProductMapper.toEntity(dto.getProduct()));
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setAddedAt(dto.getAddedAt());
        return entity;
    }
    public static List<CartItemDto> toDtoList (List<CartItemEntity> entityList) {
        return entityList.stream().map(CartItemMapper::toDto).collect(Collectors.toList());
    }
}
