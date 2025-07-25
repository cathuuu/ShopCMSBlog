package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.entites.CommentEntity;

import java.util.List;

public interface CommentRepository extends CommonRepository<CommentEntity, Long>, CommentRepositoryCustom{
    List<CommentDto> findByPost_Id(Long postId);
}
