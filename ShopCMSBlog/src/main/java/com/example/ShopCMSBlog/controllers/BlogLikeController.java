package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;
import com.example.ShopCMSBlog.services.BlogLikeService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(UrlUtils.BlogLike_URL)
public class BlogLikeController {

    private final BlogLikeService blogLikeService;

    public BlogLikeController(BlogLikeService blogLikeService) {
        this.blogLikeService = blogLikeService;
    }

    @Secured("ADMIN")
    @GetMapping("/findbyid")
    public ResponseEntity<Object> findLikeById(@RequestParam Long id) {
        var result = blogLikeService.findLikeById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findbypost")
    public ResponseEntity<Object> findLikeByPost(@RequestParam Long id) {
        var result = blogLikeService.findByPost(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getBlogLikes(@RequestBody BlogLikeQueryDto blogLikeQueryDto) {
        var result = blogLikeService.getBlogLike(blogLikeQueryDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> creatBlogLike(@RequestBody BlogLikeDto dto) {
        var result = blogLikeService.save((List<BlogLikeEntity>) dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateBlogLike(@RequestBody BlogLikeDto dto) {
        var result = blogLikeService.save((List<BlogLikeEntity>) dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        blogLikeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

}
