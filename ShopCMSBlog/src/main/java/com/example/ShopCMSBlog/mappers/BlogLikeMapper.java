package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BlogLikeMapper {
    public static BlogLikeDto toDto(BlogLikeEntity entity) {
        BlogLikeDto dto = new BlogLikeDto();
        dto.setId(entity.getId());
        dto.setPostId(entity.getPostId());
        dto.setUserId(entity.getUserId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static BlogLikeEntity toEntity(BlogLikeDto dto) {
        BlogLikeEntity entity = new BlogLikeEntity();
        entity.setId(entity.getId());
        entity.setPostId(dto.getPostId());
        entity.setUserId(dto.getUserId());
        entity.setCreatedAt(entity.getCreatedAt());
        return entity;
    }
    public static List<BlogLikeDto> toDtoList (List<BlogLikeEntity> entityList) {
        return entityList.stream().map(BlogLikeMapper::toDto).collect(Collectors.toList());
    }
}
