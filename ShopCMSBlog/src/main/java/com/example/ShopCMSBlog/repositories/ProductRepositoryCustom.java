package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {
    Page<ProductDto> getProduct(ProductQueryDto productQueryDto);
}
