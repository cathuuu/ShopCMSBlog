package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.entites.OrderEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.OrderMapper;
import com.example.ShopCMSBlog.repositories.OrderRepository;
import com.example.ShopCMSBlog.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends CommonServiceImpl<OrderEntity, Long, OrderRepository> implements OrderService {
    private final OrderMapper orderMapper;
    public OrderServiceImpl(OrderRepository repo, OrderMapper orderMapper) {
        super(repo);
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return repo.getOrderById(id);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orders = repo.findAll();
        return orderMapper.toDtoList(orders);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return repo.getOrdersByCustomerId(customerId);
    }

    @Override
    public OrderDto save(OrderDto order) {
       OrderEntity orderEntity = orderMapper.toEntity(order);
       OrderEntity savedOrderEntity = repo.save(orderEntity);
       return orderMapper.toDto(savedOrderEntity);
    }

    @Override
    public OrderDto deleteOrder(Long id) {

        OrderEntity orderEntity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond user with this ID: " + id + ". Cann't delete!."));

        repo.delete(orderEntity);
        return orderMapper.toDto(orderEntity);
    }

    @Override
    public Page<OrderDto> getOrder(OrderQueryDto orderQueryDto) {
        return repo.getOrder(orderQueryDto);
    }

}
