package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.entites.OrderEntity;
import com.example.ShopCMSBlog.entites.UserEntity;

import java.util.List;

public interface OrderService extends CommonService<OrderEntity, Long> {
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByCustomerId(Long customerId);
    OrderDto save(OrderDto order);
    OrderDto deleteOrder(Long id);
}
