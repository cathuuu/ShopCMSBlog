package com.example.ShopCMSBlog.dtos.Queries;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostQueryDto extends QueryDto{
    Long id;
    String title;
    String content;
    String imageUrl;
    LocalDateTime createdAt;
    Long author;
}
