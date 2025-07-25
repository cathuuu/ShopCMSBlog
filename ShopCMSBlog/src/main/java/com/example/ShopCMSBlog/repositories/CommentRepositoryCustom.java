package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import org.springframework.data.domain.Page;

public interface CommentRepositoryCustom {
    Page<CommentDto> getComment(CommentQueryDto commentQueryDto);
}
