package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;

public interface CustomerRepository extends CommonRepository<CustomerEntity, Long>,CustomerRepositoryCustom {
    CustomerDto getCustomerById(Long id);
}
