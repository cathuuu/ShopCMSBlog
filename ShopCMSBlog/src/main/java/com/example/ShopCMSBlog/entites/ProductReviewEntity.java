package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "product_id")
    Long productId;

    @JoinColumn(name = "customer_id")
    Long customerId;

    @Column(name = "rating",nullable = false)
    Integer rating;

    @Column(name = "comment",columnDefinition = "TEXT")
    String comment;

    @Column(name = "created_at",nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
