package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.Queries.CartItemQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import org.springframework.data.domain.Page;

public interface CartItemRepositoryCustom {
    Page<CartItemDto> getCartItem(CartItemQueryDto cartItemQueryDto);
}
