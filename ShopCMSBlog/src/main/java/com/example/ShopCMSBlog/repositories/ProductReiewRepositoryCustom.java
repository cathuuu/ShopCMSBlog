package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductReiewRepositoryCustom {
    Page<ProductReviewDto>getProductReiew(ProductReviewQueryDto productReviewQueryDto);
}
