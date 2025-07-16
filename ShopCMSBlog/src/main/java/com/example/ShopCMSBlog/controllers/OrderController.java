package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.OrderDto;
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
    public ResponseEntity<Object> getOrder(@RequestParam Long id) {
        var result = orderService.getOrdersByCustomerId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAllOrders() {
        var result = orderService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> saveOrder(@RequestBody OrderDto user) {
        var result = orderService.save(user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
