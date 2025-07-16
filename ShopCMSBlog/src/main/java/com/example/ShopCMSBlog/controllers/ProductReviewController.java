package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.entites.ProductReviewEntity;
import com.example.ShopCMSBlog.mappers.ProductReviewMapper;
import com.example.ShopCMSBlog.services.ProductReviewService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


    @Slf4j
    @RestController
    @RequestMapping(UrlUtils.ProductReview_URL)
    public class ProductReviewController {
        private final ProductReviewService productReviewService;

        public ProductReviewController(ProductReviewService productReviewService) {
            this.productReviewService = productReviewService;

        }


        @GetMapping("/search")
        public ResponseEntity<Object> findByIdAndComment(@RequestParam Long id, @RequestParam String comment) {
            var result = productReviewService.findByIdAndComment(id, comment);
            return ResponseEntity.ok(result);
        }

        @GetMapping()
        public ResponseEntity<Object> findAll() {
            var result = productReviewService.getAll();
            List<ProductReviewDto> list = ProductReviewMapper.toDtoList(result);
            return ResponseEntity.ok(list);
        }

        @PostMapping()
        public ResponseEntity<Object> create(@RequestBody ProductReviewEntity product) {
            var result = productReviewService.save(product);
            return ResponseEntity.ok(result);
        }

        @PutMapping()
        public ResponseEntity<Object> update(@RequestBody ProductReviewEntity product) {
            var result = productReviewService.save(product);
            return ResponseEntity.ok(result);
        }

        @DeleteMapping()
        public ResponseEntity<Object> delete(@RequestParam Long id) {
            productReviewService.delete(id);
            return ResponseEntity.noContent().build();
        }
}
