package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductMapper {
    public static ProductDto toDto(ProductEntity entity) {
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStockQuantity(entity.getStokeQuantity());
        dto.setCategoryId(entity.getCategoryId());
        dto.setSupplierId(entity.getSupplierId());
        return dto;
    }
    public static ProductEntity toEntity(ProductDto dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
        entity.setStokeQuantity(dto.getStockQuantity());
        entity.setCategoryId(dto.getCategoryId());
        entity.setSupplierId(dto.getSupplierId());
        return entity;
    }
    public static List<ProductDto> toDtoList (List<ProductEntity> entityList) {
        return entityList.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }
}
