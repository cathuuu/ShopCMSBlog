package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;

import java.util.List;

public interface CartItemRepository extends CommonRepository<CartItemEntity, Long>, CartItemRepositoryCustom {
    List<CartItemEntity> findByCartId(Long cartId);

    void deleteByCartId(Long cartId);
}
