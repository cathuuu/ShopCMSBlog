package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.services.CartItemService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlUtils.CartItem_URL)
public class CartItiemController {


    private final CartItemService cartItiemService;


    public CartItiemController(CartItemService cartItiemService) {
        this.cartItiemService = cartItiemService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getCartItemById(@RequestParam Long id) {
        var result = cartItiemService.getCartItemById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findbycart")
    public ResponseEntity<Object> getCartItemByCart(@RequestParam Long id) {
        var result = cartItiemService.getItemsByCart(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping()
    public ResponseEntity<Object> findAll() {
        var result = cartItiemService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> createCart(@RequestBody CartItemDto dto) {
        var result = cartItiemService.saveCartItem(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateCart(@RequestBody CartItemDto dto) {
        var result = cartItiemService.saveCartItem(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        cartItiemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

}
