package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.OrderDetailDto;
import com.example.ShopCMSBlog.entites.OrderDetailEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService extends CommonService<OrderDetailEntity, Long> {
    List<OrderDetailDto> getDetailsByOrderId(Long orderId);
}
