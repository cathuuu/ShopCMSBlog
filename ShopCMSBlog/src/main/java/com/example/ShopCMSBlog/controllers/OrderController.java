package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.services.OrderService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlUtils.Order_URL)
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getOrder(@RequestBody OrderQueryDto orderQueryDto) {
        var result = orderService.getOrder(orderQueryDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAllOrders() {
        var result = orderService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        var result = orderService.createOrder(orderDto);
        return ResponseEntity.ok(result);
    }
    @PutMapping
    public ResponseEntity<Object> updateOrder(@RequestBody OrderDto orderDto) {
        return null;
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
