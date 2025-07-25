package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import org.springframework.data.domain.Page;

public interface CartRepositoryCustom {
    Page<CartDto> getCart(CartQueryDto cartQueryDto);
}
