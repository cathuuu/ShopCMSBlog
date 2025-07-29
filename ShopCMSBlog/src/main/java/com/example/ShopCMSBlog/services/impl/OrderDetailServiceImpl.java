package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.OrderDetailDto;
import com.example.ShopCMSBlog.entites.OrderDetailEntity;
import com.example.ShopCMSBlog.mappers.OrderDetailMapper;
import com.example.ShopCMSBlog.repositories.OrderDetailRepository;
import com.example.ShopCMSBlog.services.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl extends CommonServiceImpl<OrderDetailEntity,Long,OrderDetailRepository> implements OrderDetailService {
    public OrderDetailServiceImpl(OrderDetailRepository repo) {
        super(repo);
    }

    @Override
    public List<OrderDetailDto> getDetailsByOrderId(Long orderId) {
        return repo.findByOrderId(orderId).stream().map(OrderDetailMapper::toDto)
                .collect(Collectors.toList());
    }
}
