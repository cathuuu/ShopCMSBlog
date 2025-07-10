package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;

import java.util.List;

public interface BlogLikeService extends CommonService<BlogLikeEntity,Long> {
    BlogLikeDto findLikeById(Long id);

    BlogLikeDto createLike(BlogLikeDto like);
    BlogLikeDto deleteLike(Long id);

    List<BlogLikeDto> findByPost(Long id);
}
