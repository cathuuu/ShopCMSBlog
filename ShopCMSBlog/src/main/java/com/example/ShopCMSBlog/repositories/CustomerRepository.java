package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends CommonRepository<CustomerEntity, Long>,CustomerRepositoryCustom {
    Optional<CustomerEntity> getCustomerById(Long id);
}
