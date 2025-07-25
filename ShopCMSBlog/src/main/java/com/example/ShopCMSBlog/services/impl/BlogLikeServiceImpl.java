package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.BlogLikeMapper;
import com.example.ShopCMSBlog.repositories.BlogLikeRepository;
import com.example.ShopCMSBlog.services.BlogLikeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BlogLikeServiceImpl extends CommonServiceImpl<BlogLikeEntity, Long, BlogLikeRepository> implements BlogLikeService {
    private final BlogLikeMapper blogLikeMapper;
    public BlogLikeServiceImpl(BlogLikeRepository repo, BlogLikeMapper blogLikeMapper) {
        super(repo);
        this.blogLikeMapper = blogLikeMapper;
    }

    @Override
    public BlogLikeDto findLikeById(Long id) {
        BlogLikeEntity blogLikeEntity = repo.findById(id).get();
        return blogLikeMapper.toDto(blogLikeEntity);
    }


    @Override
    public BlogLikeDto createLike(BlogLikeDto like) {
        BlogLikeEntity blogLikeEntity = blogLikeMapper.toEntity(like);
        blogLikeEntity = repo.save(blogLikeEntity);
        return blogLikeMapper.toDto(blogLikeEntity);
    }

    @Override
    public BlogLikeDto deleteLike(Long id) {
        BlogLikeEntity blogLikeToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond blog like with this ID: " + id + ". Cann't delete!."));

        repo.delete(blogLikeToDelete);
        return blogLikeMapper.toDto(blogLikeToDelete);
    }

    @Override
    public List<BlogLikeDto> findByPost(Long postId) {
        List<BlogLikeEntity> blogLikeEntities = repo.findByPostId(postId);
        return blogLikeMapper.toDtoList(blogLikeEntities);
    }

    @Override
    public Page<BlogLikeDto> getBlogLike(BlogLikeQueryDto blogLikeQueryDto) {
        return repo.getBlogLike(blogLikeQueryDto);
    }
}
