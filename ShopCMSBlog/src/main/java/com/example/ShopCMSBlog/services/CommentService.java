package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.entites.CommentEntity;

import java.util.List;

public interface CommentService extends CommonService<CommentEntity, Long> {
    CommentDto getCommentById(Long id);
    List<CommentDto> getCommentsByPost(Long postId);
    CommentDto createComment(CommentDto comment);
    CommentDto deleteComment(Long id);
}
