package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.entites.CartEntity;

public interface CartRepository extends CommonRepository<CartEntity, Long> {
    CartEntity getCartByCustomerId(Long customerId);
}
