package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;

import java.util.List;

public interface CartItemService extends CommonService<CartItemEntity, Long> {
    CartItemDto getCartItemById(Long id);
    List<CartItemDto> getItemsByCart(Long cartId);
    CartItemDto saveCartItem(CartItemDto cartItem);
    CartItemDto deleteCartItem(Long id);

}
