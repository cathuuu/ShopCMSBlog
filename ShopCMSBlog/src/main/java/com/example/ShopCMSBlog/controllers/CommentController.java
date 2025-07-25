package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import com.example.ShopCMSBlog.entites.CommentEntity;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.services.CommentService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlUtils.Comment_URL)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("/search")
    public ResponseEntity<Object> getComment(@RequestBody CommentQueryDto commentQueryDto) {
        var result = commentService.getComment(commentQueryDto);
        return ResponseEntity.ok(result);
    }
    @GetMapping()
    public ResponseEntity<Object> getAll() {
        var result = commentService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody CommentDto comment) {
        var result = commentService.save((List<CommentEntity>) comment);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
