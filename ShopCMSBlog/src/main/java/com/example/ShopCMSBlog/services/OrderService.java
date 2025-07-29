package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.entites.OrderEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService extends CommonService<OrderEntity, Long> {
    OrderDto getOrderById(Long id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByCustomerId(Long customerId);
    OrderDto save(OrderDto order);
    OrderDto deleteOrder(Long id);
    Page<OrderDto> getOrder(OrderQueryDto orderQueryDto);
    OrderDto createOrder(OrderDto order);
}
