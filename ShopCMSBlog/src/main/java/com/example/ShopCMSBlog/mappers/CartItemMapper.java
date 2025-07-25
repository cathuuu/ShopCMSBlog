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
        dto.setCartId(entity.getCartId());
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setAddedAt(entity.getAddedAt());
        return dto;
    }
    public static CartItemEntity toEntity(CartItemDto dto) {
        CartItemEntity entity = new CartItemEntity();
        entity.setId(dto.getId());
        entity.setCartId(dto.getCartId());
        entity.setProductId(dto.getProductId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setAddedAt(dto.getAddedAt());
        return entity;
    }
    public static List<CartItemDto> toDtoList (List<CartItemEntity> entityList) {
        return entityList.stream().map(CartItemMapper::toDto).collect(Collectors.toList());
    }
}
