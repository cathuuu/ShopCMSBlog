package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CommonRepository<SupplierEntity, Long> {
    List<SupplierDto> findByIdAndName(Long id, String name);
}
