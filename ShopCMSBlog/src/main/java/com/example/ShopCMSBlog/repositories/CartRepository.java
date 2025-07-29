package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.entites.CartEntity;

import java.util.Optional;

public interface CartRepository extends CommonRepository<CartEntity, Long>, CartRepositoryCustom {
    Optional<CartEntity> getCartByCustomerId(Long customerId);
}
