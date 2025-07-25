package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.Queries.CategoryQueryDto;
import com.example.ShopCMSBlog.services.CategoryService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlUtils.Category_URL)
public class CategoryController {


    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getCategoryById(@RequestBody CategoryQueryDto categoryQueryDto) {
        var result = categoryService.getCategory(categoryQueryDto);
        return ResponseEntity.ok(result);
    }


    @GetMapping()
    public ResponseEntity<Object> findAll() {
        var result = categoryService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> createCategory(@RequestBody CategoryDto dto) {
        var result = categoryService.createCategory(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateCategory(@RequestBody CategoryDto dto) {
        var result = categoryService.createCategory(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
