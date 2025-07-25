package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.PostDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.PostQueryDto;
import com.example.ShopCMSBlog.entites.CartEntity;
import org.springframework.data.domain.Page;

public interface CartService extends CommonService<CartEntity, Long> {
    CartDto getCartByCustomerId(Long customerId);
    CartDto saveCart(CartDto cart);
    CartDto deleteCart(Long id);
    Page<CartDto> getCart(CartQueryDto cartQueryDto);
}
