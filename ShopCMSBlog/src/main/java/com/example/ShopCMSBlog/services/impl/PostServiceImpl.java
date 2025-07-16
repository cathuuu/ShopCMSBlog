package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.PostEntity;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.PostMapper;
import com.example.ShopCMSBlog.repositories.PostRepository;
import com.example.ShopCMSBlog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostServiceImpl extends CommonServiceImpl<PostEntity, Long, PostRepository> implements PostService {
    private final PostMapper postMapper;
    public PostServiceImpl(PostRepository repo, PostMapper postMapper) {
        super(repo);
        this.postMapper = postMapper;
    }


    @Override
    public List<PostDto> getPostByIdAndTitle(Long id, String title) {
        return repo.getPostByIdAndTitle(id, title);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = repo.findAll();
        return postMapper.toDtoList(posts);
    }

    @Override
    public PostDto save(PostDto post) {
       PostEntity postEntity = postMapper.toEntity(post);
       PostEntity savedPostEntity = repo.save(postEntity);
       return postMapper.toDto(savedPostEntity);
    }

    @Override
    public PostDto deletePost(Long id) {
        PostEntity postEntity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond user with this ID: " + id + ". Cann't delete!."));

        repo.delete(postEntity);
        return postMapper.toDto(postEntity);
    }

}
