package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.ProductReviewDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductReviewQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.repositories.ProductReiewRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductReiewRepositoryCustomImpl implements ProductReiewRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;

    @Override
    public Page<ProductReviewDto> getProductReiew(ProductReviewQueryDto productReviewQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT pr.id, pr.product_id as product, pr.customer_id as customer, pr.rating, pr.comment, pr.created_at as createdAt FROM product_reviews pr WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM product_reviews pr WHERE 1=1");
        String orderBy = productReviewQueryDto.getSortBy();
        String orderDir = productReviewQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(productReviewQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(productReviewQueryDto.getPage(),  productReviewQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, ProductReviewDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(ProductReviewQueryDto productReviewQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long productReviewId = productReviewQueryDto.getId();
        Long productReviewProduct = productReviewQueryDto.getProductId();
        Long productReviewCustomer = productReviewQueryDto.getCustomerId();
        Integer productReviewRating = productReviewQueryDto.getRating();
        String productReviewComment = productReviewQueryDto.getComment();
 //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (productReviewProduct != null ) {
            condition.append(" AND pr.product_id LIKE :product");
            params.put("product", "%" + productReviewProduct + "%");
        }
        if (productReviewId != null) {
            condition.append(" AND pr.id LIKE :id");
            params.put("id", "%" + productReviewId + "%");
        }
        if (productReviewCustomer != null) {
            condition.append(" AND pr.customer_id LIKE :customer");
            params.put("customer", "%" + productReviewCustomer + "%");
        }
        if (productReviewRating!= null) {
            condition.append(" AND pr.rating LIKE :rating");
            params.put("rating", "%" + productReviewRating + "%");
        }
        if (!StringUtils.isBlank(productReviewComment)) {
            condition.append(" AND pr.comment LIKE :comment");
            params.put("comment", "%" + productReviewComment + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
