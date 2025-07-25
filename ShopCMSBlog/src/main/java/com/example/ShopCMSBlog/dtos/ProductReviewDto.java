package com.example.ShopCMSBlog.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReviewDto {
    Long id;
    Long productId;
    Long customerId;
    Integer rating;
    String comment;
    LocalDateTime createdAt;
}
