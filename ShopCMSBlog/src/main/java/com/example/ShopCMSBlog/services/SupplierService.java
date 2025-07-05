package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.entites.UserEntity;

import java.util.List;

public interface SupplierService extends CommonService<SupplierEntity, Long> {
    List<SupplierDto> findByIdAndName(Long id, String name);
    SupplierDto save(SupplierDto supplierDto);
    SupplierDto delete(Long id);
}
