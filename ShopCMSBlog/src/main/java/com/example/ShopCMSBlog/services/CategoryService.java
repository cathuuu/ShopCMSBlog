package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CategoryQueryDto;
import com.example.ShopCMSBlog.entites.CategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService extends CommonService<CategoryEntity, Long> {
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto category);
    CategoryDto deleteCategory(Long id);
    Page<CategoryDto> getCategory(CategoryQueryDto categoryQueryDto);
}
