package com.example.ShopCMSBlog.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {
     Long id;
     Long cartId;
     Long productId;
     Integer quantity;
     BigDecimal price;
     LocalDateTime addedAt;
}
