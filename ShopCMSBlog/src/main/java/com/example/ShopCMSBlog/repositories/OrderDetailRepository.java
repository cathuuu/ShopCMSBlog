package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.OrderDetailDto;
import com.example.ShopCMSBlog.entites.OrderDetailEntity;

import java.util.List;

public interface OrderDetailRepository extends CommonRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findByOrderId(Long orderId);
}
