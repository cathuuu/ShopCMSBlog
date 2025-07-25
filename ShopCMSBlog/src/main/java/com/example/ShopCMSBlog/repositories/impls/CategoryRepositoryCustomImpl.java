package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CategoryDto;
import com.example.ShopCMSBlog.dtos.Queries.CategoryQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.repositories.CategoryRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<CategoryDto> getCategory(CategoryQueryDto categoryQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT cg.id, cg.name , cg.description  FROM categories cg WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM categories cg WHERE 1=1");
        String orderBy = categoryQueryDto.getSortBy();
        String orderDir = categoryQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(categoryQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(categoryQueryDto.getPage(),  categoryQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, CategoryDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(CategoryQueryDto categoryQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long categoryId = categoryQueryDto.getId();
        String categoryName = categoryQueryDto.getName();
        String categoryDescription = categoryQueryDto.getDescription();
        //       String createdAt = String.valueOf(productReviewQueryDto.getCreatedAt());
        Map<String, Object> params = new HashMap<>();
        if (categoryId != null) {
            condition.append(" AND cg.id LIKE :id");
            params.put("id", "%" + categoryId + "%");
        }
        if (categoryName != null) {
            condition.append(" AND cg.customer_id LIKE :customerId");
            params.put("customerId", "%" + categoryName + "%");
        }
        if (!StringUtils.isBlank(categoryDescription)) {
            condition.append(" AND cg.description LIKE :description");
            params.put("description", "%" + categoryDescription + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }

}
