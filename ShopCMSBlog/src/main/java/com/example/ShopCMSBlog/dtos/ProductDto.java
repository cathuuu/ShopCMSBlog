package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.entites.CategoryEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    String description;
    BigDecimal price;
    String imageUrl;
    Integer stockQuantity;
    CategoryDto category;
    SupplierDto supplier;
}
