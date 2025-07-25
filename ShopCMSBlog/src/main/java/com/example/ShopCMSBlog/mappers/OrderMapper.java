package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.OrderEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderMapper {
    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();
        entity.setId(entity.getId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setTotalAmount(entity.getTotalAmount());
        entity.setStatus(entity.getStatus());
        entity.setCreatedAt(entity.getCreatedAt());
        return entity;
    }
    public static List<OrderDto> toDtoList (List<OrderEntity> entityList) {
        return entityList.stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }
}
