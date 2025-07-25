package com.example.ShopCMSBlog.dtos.Queries;

import com.example.ShopCMSBlog.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderQueryDto extends QueryDto {
    Long id;
    Long customerId;
    BigDecimal totalAmount;
    OrderStatus status;
    LocalDateTime createdAt;
}
