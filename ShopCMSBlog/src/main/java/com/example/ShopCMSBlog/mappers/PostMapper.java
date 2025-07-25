package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.PostEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PostMapper {
    public static PostDto toDto(PostEntity entity) {
        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setImageUrl(entity.getImageUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setAuthorId(entity.getAuthorId());
        return dto;
    }
    public static PostEntity toEntity(PostDto dto) {
        PostEntity entity = new PostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setImageUrl(dto.getImageUrl());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setAuthorId(dto.getAuthorId());
        return entity;
    }
    public static List<PostDto> toDtoList (List<PostEntity> entityList) {
        return entityList.stream().map(PostMapper::toDto).collect(Collectors.toList());
    }
}
