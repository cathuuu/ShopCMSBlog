package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.entites.BlogLikeEntity;

import java.util.List;

public interface BlogLikeRepository extends CommonRepository<BlogLikeEntity, Long>, BlogLikeRepositoryCustom {
    List<BlogLikeEntity> findByPostId(Long postId);
}
