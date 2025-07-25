package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.Queries.CartItemQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartItemService extends CommonService<CartItemEntity, Long> {
    CartItemDto getCartItemById(Long id);
    List<CartItemDto> getItemsByCart(Long cartId);
    CartItemDto saveCartItem(CartItemDto cartItem);
    CartItemDto deleteCartItem(Long id);
    Page<CartItemDto> getCartItem(CartItemQueryDto cartItemQueryDto);

}
