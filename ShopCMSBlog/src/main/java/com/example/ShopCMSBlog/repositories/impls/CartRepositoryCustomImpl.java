package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.BlogLikeDto;
import com.example.ShopCMSBlog.dtos.CartDto;
import com.example.ShopCMSBlog.dtos.Queries.BlogLikeQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CartQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.repositories.CartRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public class CartRepositoryCustomImpl implements CartRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<CartDto> getCart(CartQueryDto cartQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT c.id, c.customer_id as customerId, c.created_at as createdAt FROM carts c WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM carts c WHERE 1=1");
        String orderBy = cartQueryDto.getSortBy();
        String orderDir = cartQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(cartQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(cartQueryDto.getPage(),  cartQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, CartDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(CartQueryDto cartQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long cartId = cartQueryDto.getId();
        Long cartCustomerId = cartQueryDto.getCustomerId();
        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (cartId != null) {
            condition.append(" AND c.id LIKE :id");
            params.put("id", "%" + cartId + "%");
        }
        if (cartCustomerId != null) {
            condition.append(" AND c.customer_id LIKE :customerId");
            params.put("customerId", "%" + cartCustomerId + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }

}
