package com.example.ShopCMSBlog.dtos;

import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    Long id;
    CustomerDto customer;
    BigDecimal totalAmount;
    OrderStatus status;
    LocalDateTime createdAt;

}
