package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.mappers.SupplierMapper;
import com.example.ShopCMSBlog.repositories.SupplierRepository;
import com.example.ShopCMSBlog.services.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl extends CommonServiceImpl<SupplierEntity, Long, SupplierRepository> implements SupplierService {
    private final SupplierMapper supplierMapper;
    public SupplierServiceImpl(SupplierRepository repo, SupplierMapper supplierMapper) {
        super(repo);
        this.supplierMapper = supplierMapper;
    }


    private void validateWhenCreateOrUpdate(SupplierDto dto) {
        String supplierName = dto.getName();
        if (supplierName == null || supplierName.trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
    }

    @Override
    public List<SupplierDto> findByIdAndName(Long id, String name) {
        return repo.findByIdAndName(id, name);
    }

    @Override
    public SupplierDto save(SupplierDto dto) {
        validateWhenCreateOrUpdate(dto);
        SupplierEntity supplierEntity = supplierMapper.toEntity(dto);
        supplierEntity = repo.save(supplierEntity);
        return supplierMapper.toDto(supplierEntity);

    }


    @Override
    public SupplierDto delete(Long id) {

        SupplierEntity supplierEntity = repo.findById(id).orElseThrow(() ->
                new RuntimeException("Cann't found this Supplier " + id));
        repo.delete(supplierEntity);
        return supplierMapper.toDto(supplierEntity);
    }
}
