package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @Column(name = "rating",nullable = false)
    Integer rating;

    @Column(name = "comment",columnDefinition = "TEXT")
    String comment;

    @Column(name = "create_at",nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
