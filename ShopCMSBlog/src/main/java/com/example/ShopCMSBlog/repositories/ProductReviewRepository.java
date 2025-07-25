package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CommonRepository<ProductReviewEntity, Long>, ProductReiewRepositoryCustom {
    List<ProductReviewDto> findByIdAndComment(Long id, String comment);
}
