package com.example.ShopCMSBlog.entites;

import com.example.ShopCMSBlog.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne @JoinColumn(name = "customer_id")
    CustomerEntity customer;
    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt = LocalDateTime.now();
}
