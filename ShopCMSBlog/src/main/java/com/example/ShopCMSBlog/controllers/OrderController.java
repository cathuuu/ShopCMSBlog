package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.services.OrderService;
import com.example.ShopCMSBlog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/posts")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getUsers(@RequestParam Long id) {
        var result = orderService.getOrdersByCustomerId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAllUsers() {
        var result = orderService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody OrderDto user) {
        var result = orderService.save(user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
