package com.example.ShopCMSBlog.dtos.Queries;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.ProductDto;
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
public class ProductReviewQueryDto extends QueryDto{
    Long id;
    Long productId;
    Long customerId;
    Integer rating;
    String comment;
    LocalDateTime createdAt;
}
