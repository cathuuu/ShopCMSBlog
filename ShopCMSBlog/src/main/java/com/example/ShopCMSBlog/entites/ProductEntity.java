package com.example.ShopCMSBlog.entites;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "name", unique = true, nullable = false)
    String name;
    @Column(name = "description", unique = true, nullable = false)
    String description;
    @Column(name = "price", unique = true, nullable = false)
    BigDecimal price;
    @Column(name = "stoke_quantity", unique = true, nullable = false)
    Integer stokeQuantity;
    @Column(name ="image_url")
    String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity category;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    SupplierEntity supplier;
}
