package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.Queries.CartItemQueryDto;
import com.example.ShopCMSBlog.entites.CartItemEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.CartItemMapper;
import com.example.ShopCMSBlog.repositories.CartItemRepository;
import com.example.ShopCMSBlog.services.CartItemService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl extends CommonServiceImpl<CartItemEntity, Long, CartItemRepository> implements CartItemService {
    private final CartItemMapper cartItemMapper;
    public CartItemServiceImpl(CartItemRepository repo, CartItemMapper cartItemMapper) {
        super(repo);
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartItemDto getCartItemById(Long id) {
        Optional<CartItemEntity> cartEntity = repo.findById(id);
        return cartItemMapper.toDto(cartEntity.orElse(null));
    }

    @Override
    public List<CartItemDto> getItemsByCart(Long cartId) {
        List<CartItemEntity>cartItemEntities = repo.findAllById(Collections.singleton(cartId));
        return cartItemMapper.toDtoList(cartItemEntities);
    }

    @Override
    public CartItemDto saveCartItem(CartItemDto cartItem) {
        return cartItemMapper.toDto(repo.save(cartItemMapper.toEntity(cartItem)));
    }

    @Override
    public CartItemDto deleteCartItem(Long id) {
        CartItemEntity cartToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond CartItem with this ID: " + id + ". Cann't delete!."));

        repo.delete(cartToDelete);
        return cartItemMapper.toDto(cartToDelete);
    }

    @Override
    public Page<CartItemDto> getCartItem(CartItemQueryDto cartItemQueryDto) {
        return repo.getCartItem(cartItemQueryDto);
    }
}
