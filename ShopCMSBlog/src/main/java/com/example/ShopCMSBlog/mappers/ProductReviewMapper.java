package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductReviewMapper {
    public static ProductReviewDto toDto(ProductReviewEntity entity) {
        ProductReviewDto dto = new ProductReviewDto();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProductId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static ProductReviewEntity toEntity(ProductReviewDto dto) {
        ProductReviewEntity entity = new ProductReviewEntity();
        entity.setId(dto.getId());
        entity.setProductId(dto.getProductId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setRating(dto.getRating());
        entity.setComment(dto.getComment());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
    public static List<ProductReviewDto> toDtoList (List<ProductReviewEntity> entityList) {
        return entityList.stream().map(ProductReviewMapper::toDto).collect(Collectors.toList());
    }


}
