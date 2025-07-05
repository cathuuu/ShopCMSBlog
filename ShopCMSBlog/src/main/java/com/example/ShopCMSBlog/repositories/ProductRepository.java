package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.ProductDto;
import com.example.ShopCMSBlog.entites.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CommonRepository<ProductEntity, Long>{
    List<ProductDto> findByIdAndName(Long id, String name);
}
