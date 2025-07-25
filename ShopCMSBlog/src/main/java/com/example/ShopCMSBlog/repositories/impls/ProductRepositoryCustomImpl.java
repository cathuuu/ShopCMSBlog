package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.repositories.ProductRepositoryCustom;
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
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<ProductDto> getProduct(ProductQueryDto productQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT p.id, p.name, p.description , p.price , p.image_url as imageUrl , p.stoke_quantity as stokeQuantity, p.category_id as categoryId, p.supplierId as supplier_id  FROM products p WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM products p WHERE 1=1");
        String orderBy = productQueryDto.getSortBy();
        String orderDir = productQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(productQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(productQueryDto.getPage(),  productQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, ProductDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(ProductQueryDto productQueryDto) {
        StringBuilder condition = new StringBuilder();
        Long productId = productQueryDto.getId();
        String productName = productQueryDto.getName();
        String productDescription = productQueryDto.getDescription();
        BigDecimal productPrice = productQueryDto.getPrice();
        Integer productStokeQuantity = productQueryDto.getStockQuantity();
        String productImageUrl = productQueryDto.getImageUrl();
        Long categoryId = productQueryDto.getCategoryId();
        Long supplierId = productQueryDto.getSupplierId();
        Map<String, Object> params = new HashMap<>();
        if (productId != null) {
            condition.append(" AND p.id LIKE :id");
            params.put("id", "%" + productId + "%");
        }
        if (productPrice != null) {
            condition.append(" AND p.price LIKE :price");
            params.put("price", "%" + productPrice + "%");
        }
        if (productStokeQuantity != null) {
            condition.append(" AND p.stoke_quantity LIKE :stockQuantity");
            params.put("stockQuantity", "%" + productStokeQuantity + "%");
        }
        if (categoryId != null) {
            condition.append(" AND p.category_id LIKE :categoryId");
            params.put("categoryId", "%" + categoryId + "%");
        }
        if (supplierId != null) {
            condition.append(" AND p.supplier_id LIKE :supplierId");
            params.put("supplierId", "%" + supplierId + "%");
        }
        if (!StringUtils.isBlank(productName)) {
            condition.append(" AND p.name LIKE :name");
            params.put("name", "%" + productName + "%");
        }

        if (!StringUtils.isBlank(productDescription)) {
            condition.append(" AND p.description LIKE :description");
            params.put("description", "%" + productDescription + "%");
        }
        if (!StringUtils.isBlank(productImageUrl)) {
            condition.append(" AND p.image_url LIKE :imageUrl");
            params.put("imageUrl", "%" + productImageUrl + "%");
        }

        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
