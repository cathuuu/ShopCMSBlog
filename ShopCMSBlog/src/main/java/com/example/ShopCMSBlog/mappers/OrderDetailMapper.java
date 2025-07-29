package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.OrderDetailDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.entites.OrderDetailEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailMapper {
    public static OrderDetailDto toDto(OrderDetailEntity entity) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setQuantity(entity.getQuantity());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static OrderDetailEntity toEntity(OrderDetailDto dto) {
        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setId(dto.getId());
        entity.setOrderId(dto.getOrderId());
        entity.setQuantity(dto.getQuantity());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
    public static List<OrderDetailDto> toDto(List<OrderDetailEntity> entityList) {
        return entityList.stream().map(OrderDetailMapper::toDto).collect(Collectors.toList());
    }
}
