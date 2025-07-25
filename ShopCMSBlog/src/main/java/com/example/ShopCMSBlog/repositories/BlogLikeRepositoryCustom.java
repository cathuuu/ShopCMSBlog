package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import org.springframework.data.domain.Page;

public interface BlogLikeRepositoryCustom {
    Page<BlogLikeDto> getBlogLike(BlogLikeQueryDto blogLikeQueryDto);
}
