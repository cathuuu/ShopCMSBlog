package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.entites.CategoryEntity;

public interface CategoryRepository extends CommonRepository<CategoryEntity, Long> {
    CategoryEntity getCategoryById(Long id);
}
