package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;

import java.util.List;

public interface CustomerService extends CommonService<CustomerEntity, Long> {
    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getAllCustomers();
    CustomerDto saveCustomer(CustomerDto customer);
    CustomerDto deleteCustomer(Long id);
}
