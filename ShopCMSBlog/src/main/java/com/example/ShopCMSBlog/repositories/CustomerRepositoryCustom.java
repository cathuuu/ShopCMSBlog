package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.Queries.CustomerQueryDto;
import org.springframework.data.domain.Page;

public interface CustomerRepositoryCustom {
    Page<CustomerDto> getCustomer(CustomerQueryDto customerQueryDto);
}
