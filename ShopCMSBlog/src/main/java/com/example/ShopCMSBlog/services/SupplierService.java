package com.example.ShopCMSBlog.services;

import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SupplierService extends CommonService<SupplierEntity, Long> {

    List<SupplierDto> findByIdAndName(Long id, String name);

    SupplierDto save(SupplierDto supplierDto);

    SupplierDto delete(Long id);

    Page<SupplierEntity> getAllSuppliers(Pageable pageable);

    Optional<SupplierEntity> getSupplierById(Long id);

    List<SupplierEntity> getSuppliersByAddress(String address);

    Page<SupplierDto>getSupplies(SupplierQueryDto supplierQueryDto);
}

