package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogLikeService extends CommonService<BlogLikeEntity,Long> {
    BlogLikeDto findLikeById(Long id);

    BlogLikeDto createLike(BlogLikeDto like);
    BlogLikeDto deleteLike(Long id);

    List<BlogLikeDto> findByPost(Long id);
    Page<BlogLikeDto> getBlogLike(BlogLikeQueryDto blogLikeQueryDto);
}
