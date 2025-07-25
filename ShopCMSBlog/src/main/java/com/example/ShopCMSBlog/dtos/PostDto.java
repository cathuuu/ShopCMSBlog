package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.entites.CommentEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    Long id;
    String title;
    String content;
    String imageUrl;
    LocalDateTime createdAt;
    Long authorId;
}
