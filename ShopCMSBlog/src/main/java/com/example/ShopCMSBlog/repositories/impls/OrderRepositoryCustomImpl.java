package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.OrderQueryDto;
import com.example.ShopCMSBlog.repositories.OrderRepositoryCustom;
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
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;

    @Override
    public Page<OrderDto> getOrder(OrderQueryDto orderQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT od.id, od.customer_id as customerId, od.total_amount as totalAmount, od.status , od.created_at as createdAt FROM orders od WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM orders od WHERE 1=1");
        String orderBy = orderQueryDto.getSortBy();
        String orderDir = orderQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(orderQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(orderQueryDto.getPage(),  orderQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, OrderDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(OrderQueryDto orderQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long orderId = orderQueryDto.getId();
        Long orderCustomerId = orderQueryDto.getCustomerId();
        BigDecimal orderTotalAmount = orderQueryDto.getTotalAmount();
        String status = orderQueryDto.getStatus().toString();
        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (orderId != null) {
            condition.append(" AND od.id LIKE :id");
            params.put("id", "%" + orderId + "%");
        }
        if (orderCustomerId != null) {
            condition.append(" AND od.customer_id LIKE :customerId");
            params.put("customerId", "%" + orderCustomerId + "%");
        }
        if (orderTotalAmount != null) {
            condition.append(" AND od.total_amount LIKE :totalAmount");
            params.put("totalAmount", "%" + orderTotalAmount + "%");
        }
        if (!StringUtils.isBlank(status)) {
            condition.append(" AND od.status LIKE :status");
            params.put("status", "%" + status + "%");
        }
//        if (!StringUtils.isBlank(createdAt)) {
//            condition.append(" AND pr.created_at LIKE :createdAt");
//            params.put("createdAt", "%" + createdAt + "%");
//        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
