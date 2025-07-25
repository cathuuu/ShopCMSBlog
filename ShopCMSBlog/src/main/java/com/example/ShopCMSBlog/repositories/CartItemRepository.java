package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;

public interface CartItemRepository extends CommonRepository<CartItemEntity, Long>, CartItemRepositoryCustom {
}
