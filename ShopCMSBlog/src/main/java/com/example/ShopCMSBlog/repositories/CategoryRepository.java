package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.entites.CategoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CommonRepository<CategoryEntity, Long> , CategoryRepositoryCustom{
    CategoryEntity getCategoryById(Long id);
}
