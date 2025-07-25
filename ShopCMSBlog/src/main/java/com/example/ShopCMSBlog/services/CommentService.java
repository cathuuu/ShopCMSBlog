package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import com.example.ShopCMSBlog.entites.CommentEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService extends CommonService<CommentEntity, Long> {
    CommentDto getCommentById(Long id);
    List<CommentDto> getCommentsByPost(Long postId);
    CommentDto createComment(CommentDto comment);
    CommentDto deleteComment(Long id);
    Page<CommentDto> getComment(CommentQueryDto commentQueryDto);
}
