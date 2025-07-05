package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.mappers.CustomerMapper;
import com.example.ShopCMSBlog.repositories.CustomerRepository;
import com.example.ShopCMSBlog.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends CommonServiceImpl<CustomerEntity, Long, CustomerRepository> implements CustomerService {
    private final CustomerMapper customerMapper;
    public CustomerServiceImpl(CustomerRepository repo, CustomerMapper customerMapper) {
        super(repo);
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return repo.getCustomerById(id);
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
}
