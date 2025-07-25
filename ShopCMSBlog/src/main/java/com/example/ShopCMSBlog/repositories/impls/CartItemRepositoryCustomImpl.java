package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CartItemDto;
import com.example.ShopCMSBlog.dtos.Queries.CartItemQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.repositories.CartItemRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class CartItemRepositoryCustomImpl implements CartItemRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<CartItemDto> getCartItem(CartItemQueryDto cartItemQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT ci.id, ci.cart_id as cartId, ci.product_id as productId, ci.quantity, ci.price, ci.added_at as addedAt FROM cart_items ci WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM cart_items ci WHERE 1=1");
        String orderBy = cartItemQueryDto.getSortBy();
        String orderDir = cartItemQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(cartItemQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(cartItemQueryDto.getPage(),  cartItemQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, CartItemDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(CartItemQueryDto cartItemQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long cartItiemId = cartItemQueryDto.getId();
        Long cartItiemCartId = cartItemQueryDto.getCartId();
        Integer cartItiemQuantity = cartItemQueryDto.getQuantity();
        BigDecimal cartItiemPrice = cartItemQueryDto.getPrice();

        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (cartItiemId != null) {
            condition.append(" AND ci.id LIKE :id");
            params.put("id", "%" + cartItiemId + "%");
        }
        if (cartItiemCartId != null) {
            condition.append(" AND ci.cart_id LIKE :cartId");
            params.put("cartId", "%" + cartItiemCartId + "%");
        }
        if (cartItiemQuantity != null) {
            condition.append(" AND ci.quantity LIKE :quantity");
            params.put("quantity", "%" + cartItiemQuantity + "%");
        }
        if (cartItiemPrice != null) {
            condition.append(" AND ci.price LIKE :price");
            params.put("price", "%" + cartItiemCartId + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
