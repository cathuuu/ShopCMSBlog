package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import com.example.ShopCMSBlog.entites.CommentEntity;
import com.example.ShopCMSBlog.mappers.CommentMapper;
import com.example.ShopCMSBlog.repositories.CommentRepository;
import com.example.ShopCMSBlog.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends CommonServiceImpl<CommentEntity, Long, CommentRepository> implements CommentService {
    private final CommentMapper commentMapper;
    public CommentServiceImpl(CommentRepository repo, CommentMapper commentMapper) {
        super(repo);
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDto getCommentById(Long id) {
        CommentEntity commentEntity = repo.findById(id).orElse(null);
        return commentMapper.toDto(commentEntity);
    }

    @Override
    public List<CommentDto> getCommentsByPost(Long postId) {
        return repo.findByPostId(postId);
    }

    @Override
    public CommentDto createComment(CommentDto comment) {
        CommentEntity commentEntity = commentMapper.toEntity(comment);
        commentEntity = repo.save(commentEntity);
        return commentMapper.toDto(commentEntity);
    }

    @Override
    public CommentDto deleteComment(Long id) {

        CommentEntity commentToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond comment with this ID: " + id + ". Cann't delete!."));

        repo.delete(commentToDelete);
        return commentMapper.toDto(commentToDelete);
    }

    @Override
    public Page<CommentDto> getComment(CommentQueryDto commentQueryDto) {
        return repo.getComment(commentQueryDto);
    }
}
