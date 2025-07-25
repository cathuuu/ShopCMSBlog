package com.example.ShopCMSBlog.dtos.Queries;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductQueryDto extends QueryDto {
    Long id;
    String name;
    String description;
    BigDecimal price;
    String imageUrl;
    Integer stockQuantity;
    Long categoryId;
    Long supplierId;
}
