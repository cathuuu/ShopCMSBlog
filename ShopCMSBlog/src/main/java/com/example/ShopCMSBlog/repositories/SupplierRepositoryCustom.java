package com.example.ShopCMSBlog.repositories;

import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SupplierRepositoryCustom {

    Page<SupplierEntity> findAllSuppliersPagedNative(Pageable pageable);


    Optional<SupplierEntity> findByIdNative(Long id);


    List<SupplierEntity> findSuppliersByAddressNative(String address);

    Page<SupplierDto>getSupplies(SupplierQueryDto supplierQueryDto);

}
