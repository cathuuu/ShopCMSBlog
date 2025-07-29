package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order_id", nullable = false)
    Long orderId;


    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(nullable = false)
    Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    @Column(name = "created_at")
    LocalDateTime createdAt = LocalDateTime.now();

}

