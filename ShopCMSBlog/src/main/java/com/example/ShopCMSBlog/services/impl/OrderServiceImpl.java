package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.*;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.entites.*;
import com.example.ShopCMSBlog.enums.OrderStatus;
import com.example.ShopCMSBlog.exceptions.AppException;
import com.example.ShopCMSBlog.mappers.OrderMapper;
import com.example.ShopCMSBlog.repositories.*;
import com.example.ShopCMSBlog.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends CommonServiceImpl<OrderEntity, Long, OrderRepository> implements OrderService {
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository repo, OrderMapper orderMapper, CustomerRepository customerRepository, CartRepository cartRepository, OrderRepository orderRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, OrderRepository orderRepository1, OrderDetailRepository orderDetailRepository) {
        super(repo);
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository1;
        this.orderDetailRepository = orderDetailRepository;
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

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto order) {
        CustomerEntity customerEntity = customerRepository.getCustomerById(order.getCustomerId())
                .orElseThrow(() -> new AppException("Customer not found"));
        CartEntity cartEntity = cartRepository.getCartByCustomerId(customerEntity.getId())
                .orElseThrow(() -> new AppException("Cart not found"));
        List<CartItemEntity> cartItems = cartItemRepository.findByCartId(cartEntity.getId());
        if(cartItems.isEmpty())
        {
            throw new IllegalStateException("Cart is empty");
        }
        BigDecimal total = calculateTotal(cartItems);

        OrderEntity orderEntity = saveOrder(customerEntity.getId(), total);

        saveOrderDetails(cartItems, orderEntity);

        clearCart(cartEntity);
        return orderMapper.toDto(orderEntity);
        }
    private BigDecimal calculateTotal(List<CartItemEntity> cartItems) {
        return cartItems.stream()
                .map(item -> {
                    ProductEntity product = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new AppException("Product not found"));
                    return product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderEntity saveOrder(Long customerId, BigDecimal total) {
        OrderEntity order = new OrderEntity();
        order.setCustomerId(customerId);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(total);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    private void saveOrderDetails(List<CartItemEntity> cartItems, OrderEntity order) {
        List<OrderDetailEntity> orderDetails = cartItems.stream().map(item -> {
            ProductEntity product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new AppException("Product not found"));
            OrderDetailEntity detail = new OrderDetailEntity();
            detail.setOrderId(order.getId());
            detail.setProductId(product.getId());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(product.getPrice());
            detail.setCreatedAt(LocalDateTime.now());
            return detail;
        }).collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);
    }

    private void clearCart(CartEntity cart) {
        cartItemRepository.deleteByCartId((cart.getId()));
        cartRepository.delete(cart);
    }
}
