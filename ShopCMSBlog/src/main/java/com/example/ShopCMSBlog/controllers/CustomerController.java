package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.OrderDto;
import com.example.ShopCMSBlog.dtos.Queries.CustomerQueryDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.enums.Gender;
import com.example.ShopCMSBlog.services.CustomerService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(UrlUtils.Customer_URL)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getCustomer(@RequestBody CustomerQueryDto customerQueryDto) {
        var result = customerService.getCustomer(customerQueryDto);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<Object> getAll() {
        var result = customerService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody OrderDto order) {
        var result = customerService.save((List<CustomerEntity>) order);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<Object> deleteCustomer(@RequestParam Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}