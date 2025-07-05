package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.entites.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CommonRepository<PostEntity, Long> {

    List<PostDto> getPostByIdAndTitle(Long id, String title);
}
