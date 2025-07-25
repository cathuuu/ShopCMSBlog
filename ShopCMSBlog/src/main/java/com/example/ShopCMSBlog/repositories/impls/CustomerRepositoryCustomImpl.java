package com.example.ShopCMSBlog.repositories.impls;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.Queries.ConditionDto;
import com.example.ShopCMSBlog.dtos.Queries.CustomerQueryDto;
import com.example.ShopCMSBlog.repositories.CustomerRepositoryCustom;
import com.example.ShopCMSBlog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @Autowired
    private BaseNativeQuery baseNativeQuery;
    @Override
    public Page<CustomerDto> getCustomer(CustomerQueryDto customerQueryDto) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT cu.id, cu.full_name as fullName, cu.phone_number as phoneNumber, cu.address , cu.gender, cu.date_of_birth as dateOfBirth, cu.user_id as userId FROM customers cu WHERE 1=1");
        StringBuilder countSqlBuilder = new StringBuilder("SELECT COUNT(*) FROM customers cu WHERE 1=1");
        String orderBy = customerQueryDto.getSortBy();
        String orderDir = customerQueryDto.getSortDir();
        ConditionDto conditionData = makeCondition(customerQueryDto);
        if (!StringUtils.isBlank(conditionData.getQueryCondition())) {
            sqlBuilder.append(conditionData.getQueryCondition());
            countSqlBuilder.append(conditionData.getQueryCondition());
        }
        if (!StringUtils.isBlank(orderBy)) {
            if (!StringUtils.isBlank(orderDir)) sqlBuilder.append(" ORDER BY " + orderBy + " " + orderDir);
        }
        Pageable pageable = PageRequest.of(customerQueryDto.getPage(), customerQueryDto.getSize());
        return baseNativeQuery.findPage(sqlBuilder.toString(), countSqlBuilder.toString(), pageable, CustomerDto.class, conditionData.getParams());
    }

    private ConditionDto makeCondition(CustomerQueryDto customerQueryDtoo) {
        StringBuilder condition = new StringBuilder();
        Long customerId = customerQueryDtoo.getId();
        String customerFullName = customerQueryDtoo.getFullName();
        String customerPhoneNumber = customerQueryDtoo.getPhoneNumber();
        String customerAddress = customerQueryDtoo.getAddress();
        String customerGender = customerQueryDtoo.getGender().toString();
        Long customerUserId = customerQueryDtoo.getUserId();
        String dateOfBirth = String.valueOf(customerQueryDtoo.getDateOfBirth());
        Map<String, Object> params = new HashMap<>();
        if (customerId != null) {
            condition.append(" AND cu.id LIKE :id");
            params.put("cu", "%" + customerId + "%");
        }
        if (customerUserId != null) {
            condition.append(" AND cu.user_id LIKE :userId");
            params.put("userId", "%" + customerUserId + "%");
        }
        if (!StringUtils.isBlank(customerFullName)) {
            condition.append(" AND cu.full_name LIKE :fullName");
            params.put("fullName", "%" + customerFullName + "%");
        }
        if (!StringUtils.isBlank(customerPhoneNumber)) {
            condition.append(" AND cu.phone_number LIKE :phoneNumber");
            params.put("phoneNumber", "%" + customerPhoneNumber + "%");
        }
        if (!StringUtils.isBlank(customerAddress)) {
            condition.append(" AND cu.address LIKE :address");
            params.put("address", "%" + customerAddress + "%");
        }
        if (!StringUtils.isBlank(customerGender)) {
            condition.append(" AND cu.gender LIKE :gender");
            params.put("gender", "%" + customerGender + "%");
        }
        if (!StringUtils.isBlank(dateOfBirth)) {
            condition.append(" AND cu.date_of_birth LIKE :dateOfBirth");
            params.put("dateOfBirth", "%" + dateOfBirth + "%");
        }
        return ConditionDto.builder().params(params).queryCondition(condition.toString()).build();
    }
}
