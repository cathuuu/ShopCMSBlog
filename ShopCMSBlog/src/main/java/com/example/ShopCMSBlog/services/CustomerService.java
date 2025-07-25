package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CommentDto;
import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.Queries.CommentQueryDto;
import com.example.ShopCMSBlog.dtos.Queries.CustomerQueryDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService extends CommonService<CustomerEntity, Long> {
    Page<CustomerEntity> searchCustomerWithPagination(String fullName, String phoneNumber, Long customerId, String address, Gender gender, LocalDate dateOfBirth, Pageable pageable);
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto deleteCustomer(Long id);
    Page<CustomerDto> getCustomer(CustomerQueryDto customerQueryDto);
}
