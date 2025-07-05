package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suppliers")
public class CartController {


    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getCartByCustomerId(@RequestParam Long id) {
        var result = cartService.getCartByCustomerId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        var result = cartService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> createCart(@RequestBody CartDto dto) {
        var result = cartService.saveCart(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateCart(@RequestBody CartDto dto) {
        var result = cartService.saveCart(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}
