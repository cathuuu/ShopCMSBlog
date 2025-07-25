package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {
    Page<PostDto> getPost(PostQueryDto postQueryDto);
}
