package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.services.PostService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlUtils.Posts_URL)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getPost(@RequestParam PostQueryDto postQueryDto) {
        var result = postService.getPost(postQueryDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody PostDto user) {
        var result = postService.save(user);
        return ResponseEntity.ok(result);
    }
    @PutMapping()
    public ResponseEntity<Object> update(@RequestBody PostDto product) {
        var result = postService.save(product);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        var result = postService.deletePost(id);
        return ResponseEntity.ok(result);
    }
}

