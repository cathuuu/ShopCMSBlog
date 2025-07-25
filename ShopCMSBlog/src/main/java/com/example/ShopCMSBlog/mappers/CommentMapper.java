package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.entites.CommentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentMapper {
    public static CommentDto toDto(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setPostId(entity.getPostId());
        dto.setUserId(entity.getUserId());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static CommentEntity toEntity(CommentDto dto) {
        CommentEntity entity = new CommentEntity();
        entity.setId(entity.getId());
        entity.setPostId(dto.getPostId());
        entity.setUserId(dto.getUserId());
        entity.setContent(entity.getContent());
        entity.setCreatedAt(entity.getCreatedAt());
        return entity;
    }
    public static List<CommentDto> toDtoList (List<CommentEntity> entities) {
        return entities.stream().map(CommentMapper::toDto).collect(Collectors.toList());
    }
}
