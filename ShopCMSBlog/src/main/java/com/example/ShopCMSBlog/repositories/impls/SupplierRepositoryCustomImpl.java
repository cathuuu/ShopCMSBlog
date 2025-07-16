package com.example.ShopCMSBlog.repositories.impls;


import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.repositories.SupplierRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        String sql = "SELECT s.id, s.name, s.contact_name, s.phone_number, s.email, s.address FROM suppliers s ORDER BY s.name ASC";
        String countSql = "SELECT COUNT(*) FROM suppliers";
        return baseNativeQuery.findPage(sql, countSql, pageable, SupplierEntity.class);
    }

    @Override
    public Page<SupplierEntity> searchSuppliersByCriteriaNative(String name, String contactName, String email, String phoneNumber, Pageable pageable) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT s.id, s.name, s.contact_name, s.phone_number, s.email, s.address FROM suppliers s WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM suppliers s WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (name != null && !name.isEmpty()) {
            sqlBuilder.append(" AND s.name LIKE :name");
            countSqlBuilder.append(" AND s.name LIKE :name");
            params.put("name", "%" + name + "%");
        }
        if (contactName != null && !contactName.isEmpty()) {
            sqlBuilder.append(" AND s.contact_name LIKE :contactName");
            countSqlBuilder.append(" AND s.contact_name LIKE :contactName");
            params.put("contactName", "%" + contactName + "%");
        }
        if (email != null && !email.isEmpty()) {
            sqlBuilder.append(" AND s.email LIKE :email");
            countSqlBuilder.append(" AND s.email LIKE :email");
            params.put("email", "%" + email + "%");
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            sqlBuilder.append(" AND s.phone_number LIKE :phoneNumber");
            countSqlBuilder.append(" AND s.phone_number LIKE :phoneNumber");
            params.put("phoneNumber", "%" + phoneNumber + "%");
        }

        if (pageable.getSort().isSorted()) {
            sqlBuilder.append(" ORDER BY ");
            pageable.getSort().forEach(order -> {

                sqlBuilder.append(order.getProperty()).append(" ").append(order.getDirection()).append(", ");
            });
            sqlBuilder.setLength(sqlBuilder.length() - 2);
        } else {
            sqlBuilder.append(" ORDER BY s.name ASC");
        }

        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, SupplierEntity.class, params);
    }

    @Override
    public Optional<SupplierEntity> findByIdNative(Long id) {
        String sql = "SELECT s.id, s.name, s.contact_name, s.phone_number, s.email, s.address FROM suppliers s WHERE s.id = ?";
        SupplierEntity supplier = baseNativeQuery.findOne(sql, SupplierEntity.class, id);
        return Optional.ofNullable(supplier);
    }

    @Override
    public List<SupplierEntity> findSuppliersByAddressNative(String address) {
        String sql = "SELECT s.id, s.name, s.contact_name, s.phone_number, s.email, s.address FROM suppliers s WHERE s.address LIKE :address ORDER BY s.name ASC";
        Map<String, Object> params = Map.of("address", "%" + address + "%");
        return baseNativeQuery.findList(sql, SupplierEntity.class, params);
    }
}
