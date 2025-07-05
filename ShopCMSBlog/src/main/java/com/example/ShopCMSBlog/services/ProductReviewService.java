package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;

import java.util.List;

public interface ProductReviewService extends CommonService<ProductReviewEntity, Long>
{
    List<ProductReviewDto> findByIdAndComment(Long id, String comment);
    ProductReviewDto save(ProductReviewDto productReviewDto);
    void delete(Long id);
}
