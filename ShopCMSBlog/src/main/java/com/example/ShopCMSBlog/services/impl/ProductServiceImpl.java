package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.Components.BaseNativeQuery;
import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.dtos.Queries.ProductQueryDto;
import com.example.ShopCMSBlog.entites.ProductEntity;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.exceptions.AppException;
import com.example.ShopCMSBlog.mappers.ProductMapper;
import com.example.ShopCMSBlog.repositories.ProductRepository;
import com.example.ShopCMSBlog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl extends CommonServiceImpl <ProductEntity, Long, ProductRepository> implements ProductService {
    private final ProductMapper productMapper;
    @Autowired
    private BaseNativeQuery baseNativeQuery;
    public ProductServiceImpl(ProductRepository repo, ProductMapper productMapper) {
        super(repo);
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findByIdAndName(Long id, String name) {
        return repo.findByIdAndName(id, name);
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = repo.findById(id).orElseThrow(() ->
                new AppException("Cann't found this  " + id));
        repo.delete(productEntity);
    }

    @Override
    public Page<ProductDto> getProduct(ProductQueryDto productQueryDto) {
        return repo.getProduct(productQueryDto);
    }

    @Override
    public ProductDto save(ProductDto dto) {
        validateWhenCreateOrUpdate(dto);
        ProductEntity productEntity = ProductMapper.toEntity(dto);
        productEntity = repo.save(productEntity);
        return productMapper.toDto(productEntity);
    }
    private void validateWhenCreateOrUpdate(ProductDto dto) {
        String productName = dto.getName();
        if (productName == null || productName.trim().isEmpty()) {
            throw new RuntimeException("Customer name is required");
        }
    }
}
