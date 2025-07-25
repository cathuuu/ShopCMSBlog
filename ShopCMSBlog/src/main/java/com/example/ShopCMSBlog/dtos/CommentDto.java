package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.entites.PostEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.enums.Gender;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    Long id;
    Long postId;
    Long userId;
    String content;
    LocalDateTime createdAt;
}
