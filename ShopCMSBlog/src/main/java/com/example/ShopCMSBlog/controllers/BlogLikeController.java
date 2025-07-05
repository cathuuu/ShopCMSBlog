package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.BlogLikeEntity;
import com.example.ShopCMSBlog.mappers.SupplierMapper;
import com.example.ShopCMSBlog.services.BlogLikeService;
import com.example.ShopCMSBlog.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/suppliers")
public class BlogLikeController {

    private final BlogLikeService blogLikeService;

    public BlogLikeController(BlogLikeService blogLikeService) {
        this.blogLikeService = blogLikeService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getLikeById(@RequestParam Long id) {
        var result = blogLikeService.getLikeById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> getLikeByPost(@RequestParam Long id) {
        var result = blogLikeService.getLikesByPost(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping()
    public ResponseEntity<Object> findAll() {
        var result = blogLikeService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> createSupplier(@RequestBody BlogLikeDto dto) {
        var result = blogLikeService.save((List<BlogLikeEntity>) dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateSupplier(@RequestBody BlogLikeDto dto) {
        var result = blogLikeService.save((List<BlogLikeEntity>) dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        blogLikeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

}
