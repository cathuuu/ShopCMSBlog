package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import com.example.ShopCMSBlog.entites.CartEntity;
import com.example.ShopCMSBlog.mappers.CartMapper;
import com.example.ShopCMSBlog.repositories.CartRepository;
import com.example.ShopCMSBlog.services.CartService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends CommonServiceImpl<CartEntity, Long, CartRepository> implements CartService {
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository repo, CartMapper cartMapper) {
        super(repo);
        this.cartMapper = cartMapper;
    }

    @Override
    public CartEntity getCartByCustomerId(Long customerId) {
        return repo.getCartByCustomerId(customerId).orElse(null);
    }

    @Override
    public CartDto saveCart(CartDto cart) {
        CartEntity cartEntity = cartMapper.toEntity(cart);
        cartEntity = repo.save(cartEntity);
        return cartMapper.toDto(cartEntity);
    }

    @Override
    public CartDto deleteCart(Long id) {
        CartEntity cartToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond Cart with this ID: " + id + ". Cann't delete!."));

        repo.delete(cartToDelete);
        return cartMapper.toDto(cartToDelete);
    }

    @Override
    public Page<CartDto> getCart(CartQueryDto cartQueryDto) {
        return repo.getCart(cartQueryDto);
    }
}
