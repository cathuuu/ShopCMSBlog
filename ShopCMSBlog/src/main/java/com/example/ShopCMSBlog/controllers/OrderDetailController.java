package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.OrderDetailDto;
import com.example.ShopCMSBlog.services.OrderDetailService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UrlUtils.OrderDetail_URL)
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @GetMapping("/search")
    public ResponseEntity<List<OrderDetailDto>> getDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.getDetailsByOrderId(orderId));
    }
}
