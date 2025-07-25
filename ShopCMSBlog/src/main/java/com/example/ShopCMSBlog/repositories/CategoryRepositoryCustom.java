package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartItemQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CategoryQueryDto;
import org.springframework.data.domain.Page;

public interface CategoryRepositoryCustom {
    Page<CategoryDto> getCategory(CategoryQueryDto categoryQueryDto);
}
