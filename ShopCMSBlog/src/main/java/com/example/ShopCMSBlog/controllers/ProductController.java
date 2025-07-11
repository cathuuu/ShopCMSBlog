package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.services.ProductService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping(UrlUtils.Product_URL)
    public class ProductController {
        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }


        @GetMapping("/search")
        public ResponseEntity<Object> getUsers(@RequestParam Long id, @RequestParam String name) {
            var result = productService.findByIdAndName(id, name);
            return ResponseEntity.ok(result);
        }

        @GetMapping()
        public ResponseEntity<Object> findAllUsers() {
            var result = productService.getAll();
            return ResponseEntity.ok(result);
        }

        @PostMapping()
        public ResponseEntity<Object> createUser(@RequestBody ProductEntity product) {
            var result = productService.save(product);
            return ResponseEntity.ok(result);
        }

        @PutMapping()
        public ResponseEntity<Object> updateUser(@RequestBody ProductEntity product) {
            var result = productService.save(product);
            return ResponseEntity.ok(result);
        }

        @DeleteMapping()
        public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

