package com.example.ShopCMSBlog.repositories.impls;


import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.repositories.SupplierRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class SupplierRepositoryCustomImpl implements SupplierRepositoryCustom {

    @Autowired
    private BaseNativeQuery baseNativeQuery;

    @Override
    public Page<SupplierEntity> findAllSuppliersPagedNative(Pageable pageable) {
        // SELECT tất cả các cột của SupplierEntity
        String sql = "SELECT s.id, s.name, s.contact_name as contactName, s.phone_number as phoneNumber, s.email, s.address FROM suppliers s ORDER BY s.name ASC";
        String countSql = "SELECT COUNT(*) FROM suppliers";
        return baseNativeQuery.findPage(sql, countSql, pageable, SupplierEntity.class);
    }


    @Override
    public Optional<SupplierEntity> findByIdNative(Long id) {
        String sql = "SELECT s.id, s.name, s.contact_name as contactName, s.phone_number as phoneNumber, s.email, s.address FROM suppliers s WHERE s.id = ?";
        SupplierEntity supplier = baseNativeQuery.findOne(sql, SupplierEntity.class, id);
        return Optional.ofNullable(supplier);
    }

    @Override
    public List<SupplierEntity> findSuppliersByAddressNative(String address) {
        String sql = "SELECT s.id, s.name, s.contact_name as contactName, s.phone_number as phoneNumber, s.email, s.address FROM suppliers s WHERE s.address LIKE :address ORDER BY s.name ASC";
        Map<String, Object> params = Map.of("address", "%" + address + "%");
        return baseNativeQuery.findList(sql, SupplierEntity.class, params);
    }

    @Override
    public Page<SupplierDto> getSupplies(SupplierQueryDto supplierQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT s.id, s.name, s.contact_name as contactName, s.phone_number as phoneNumber, s.email, s.address FROM suppliers s WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM suppliers s WHERE 1=1");
        String orderBy = supplierQueryDto.getSortBy();
        String orderDir = supplierQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(supplierQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if(!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy +" "+orderDir);
        }
        Pageable pageable = PageRequest.of(supplierQueryDto.getPage(),  supplierQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, SupplierDto.class,conditionData.getParams());
    }
    private ConditionDto makeCondition(SupplierQueryDto supplierQueryDto) {
        StringBuilder condition = new StringBuilder();
        String supplierName = supplierQueryDto.getName();
        String supplierAddress = supplierQueryDto.getAddress();
        Long supplierId = supplierQueryDto.getId();
        String supplierEmail = supplierQueryDto.getEmail();
        String supplierPhoneNumber = supplierQueryDto.getPhoneNumber();
        Map<String, Object> params = new HashMap<>();
        if (!StringUtils.isBlank(supplierName)) {
            condition.append(" AND s.name LIKE :name");
            params.put("name", "%" + supplierName + "%");
        }
        if (supplierId != null) {
            condition.append(" AND s.id LIKE :id");
            params.put("id", "%" + supplierId + "%");
        }
        if (!StringUtils.isBlank(supplierAddress)) {
            condition.append(" AND s.address LIKE :address");
            params.put("address", "%" + supplierAddress + "%");
        }
        if (!StringUtils.isBlank(supplierEmail)) {
            condition.append(" AND s.email LIKE :email");
            params.put("email", "%" + supplierEmail + "%");
        }
        if (!StringUtils.isBlank(supplierPhoneNumber)) {
            condition.append(" AND s.phone_number LIKE :phoneNumber");
            params.put("phoneNumber", "%" + supplierPhoneNumber + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
