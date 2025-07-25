package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import org.springframework.data.domain.Page;

public interface OrderRepositoryCustom {
    Page<OrderDto> getOrder(OrderQueryDto orderQueryDto);
}
