package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.entites.CartEntity;

public interface CartService extends CommonService<CartEntity, Long> {
    CartDto getCartByCustomerId(Long customerId);
    CartDto saveCart(CartDto cart);
    CartDto deleteCart(Long id);
}
