package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.Queries.CustomerQueryDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.enums.Gender;
import com.example.ShopCMSBlog.mappers.CustomerMapper;
import com.example.ShopCMSBlog.repositories.CustomerRepository;
import com.example.ShopCMSBlog.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends CommonServiceImpl<CustomerEntity, Long, CustomerRepository> implements CustomerService {
    private final CustomerMapper customerMapper;
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    public CustomerServiceImpl(CustomerRepository repo, CustomerMapper customerMapper) {
        super(repo);
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return repo.getCustomerById(id).orElse(null);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> list = repo.findAll();
        return customerMapper.toDtoList(list);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        return customerMapper.toDto(repo.save(customerEntity));
    }

    @Override
    public CustomerDto deleteCustomer(Long id) {
        CustomerEntity customerToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond Customer with this ID: " + id + ". Cann't delete!."));

        repo.delete(customerToDelete);
        return customerMapper.toDto(customerToDelete);
    }

    @Override
    public Page<CustomerDto> getCustomer(CustomerQueryDto customerQueryDto) {
        return repo.getCustomer(customerQueryDto);
    }

    public Page<CustomerEntity> searchCustomerWithPagination(
            String fullName, String phoneNumber, Long customerId, String address, Gender gender, LocalDate dateOfBirth, Pageable pageable) {

        Map<String, Object> queryParams = new HashMap<>();
        List<String> conditions = new java.util.ArrayList<>(); // Dùng ArrayList để xây dựng điều kiện WHERE

        if (fullName != null && !fullName.trim().isEmpty()) {
            conditions.add("u.full_name LIKE :full_nameParam");
            queryParams.put("full_nameParam", "%" + fullName.trim() + "%");
        }
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            conditions.add("u.phone_number LIKE :phone_numberParam");
            queryParams.put("phone_numberParam", "%" + phoneNumber.trim() + "%");
        }
        if (address != null && !address.trim().isEmpty()) {
            conditions.add("u.address LIKE :addressParam");
            queryParams.put("addressParam", "%" + address.trim() + "%");
        }
        if (gender != null) { // Sửa lỗi: Kiểm tra gender và gán genderParam
            conditions.add("u.gender = :genderParam"); // Sử dụng tên thuộc tính trong Entity
            queryParams.put("genderParam", gender);
        }
        if (dateOfBirth != null) {
            conditions.add("u.dateOfBirth = :dateOfBirthParam"); // Sử dụng tên thuộc tính trong Entity
            queryParams.put("dateOfBirthParam", dateOfBirth);
        }
        if (customerId != null) {
            conditions.add("u.id = :customerIdParam");
            queryParams.put("customerIdParam", customerId);
        }

        String whereClause = "";
        if (!conditions.isEmpty()) {
            whereClause = " WHERE " + String.join(" AND ", conditions);
        }

        // SQL chính để lấy dữ liệu người dùng
        // Lưu ý: Đảm bảo các cột bạn SELECT khớp với tên thuộc tính trong UserEntity
        String sql = "SELECT u.id, u.full_name, u.phone_number, u.address,u.gender,u.date_of_birth " +
                "FROM customers u" + whereClause + " ORDER BY u.id ASC";

        // SQL để đếm tổng số bản ghi
        String countSql = "SELECT COUNT(u.id) FROM customers u" + whereClause;

        // Gọi phương thức findPage từ BaseNativeQuery
        return baseNativeQuery.findPage(sql, countSql, pageable, CustomerEntity.class, queryParams);
    }
}
