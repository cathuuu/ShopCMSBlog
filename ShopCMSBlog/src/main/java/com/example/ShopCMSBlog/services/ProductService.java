package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends CommonService<ProductEntity , Long> {
    List<ProductDto> findByIdAndName(Long id, String name);

    ProductDto save(ProductDto productDto);

    void delete(Long id);
}
