package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductQueryDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.enums.Gender;
import com.example.ShopCMSBlog.services.ProductService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
    @RequestMapping(UrlUtils.Product_URL)
    public class ProductController {
        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }


        @GetMapping("/search")
        public ResponseEntity<Object> getProduct(@RequestBody ProductQueryDto productQueryDto) {
            var result = productService.getProduct(productQueryDto);
            return ResponseEntity.ok(result);
        }

        @GetMapping()
        public ResponseEntity<Object> findAllProducts() {
            var result = productService.getAll();
            return ResponseEntity.ok(result);
        }
        @Secured("ADMIN")
        @PostMapping()
        public ResponseEntity<Object> createProduct(@RequestBody ProductEntity product) {
            var result = productService.save(product);
            return ResponseEntity.ok(result);
        }
        @Secured("ADMIN")
        @PutMapping()
        public ResponseEntity<Object> updateProduct(@RequestBody ProductEntity product) {
            var result = productService.save(product);
            return ResponseEntity.ok(result);
        }
        @Secured("ADMIN")
        @DeleteMapping()
        public ResponseEntity<Object> deleteProduct(@RequestParam Long id) {
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }

    }

