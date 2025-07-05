package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.entites.OrderEntity;

import java.util.List;

public interface OrderRepository extends CommonRepository<OrderEntity, Long> {
    OrderDto getOrderById(Long id);

    List<OrderDto> getOrdersByCustomerId(Long customerId);
}
