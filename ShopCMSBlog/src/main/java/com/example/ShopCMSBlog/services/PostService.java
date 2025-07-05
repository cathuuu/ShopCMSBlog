package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.entites.PostEntity;

import java.util.List;

public interface PostService extends CommonService<PostEntity, Long> {

    List<PostDto> getPostByIdAndTitle(Long id, String title);
    List<PostDto> getAllPosts();
    PostDto save(PostDto post);
    PostDto deletePost(Long id);
}
