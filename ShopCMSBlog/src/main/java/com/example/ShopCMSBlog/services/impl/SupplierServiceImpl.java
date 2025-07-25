package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.mappers.SupplierMapper;
import com.example.ShopCMSBlog.repositories.SupplierRepository;
import com.example.ShopCMSBlog.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl extends CommonServiceImpl<SupplierEntity, Long, SupplierRepository> implements SupplierService {
    private final SupplierMapper supplierMapper;
    @Autowired
    private SupplierRepository supplierRepository;
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
    @Override
    public Page<SupplierEntity> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAllSuppliersPagedNative(pageable);
    }

    @Override
    public Optional<SupplierEntity> getSupplierById(Long id) {
        return supplierRepository.findByIdNative(id);
    }
    @Override
    public List<SupplierEntity> getSuppliersByAddress(String address) {
        return supplierRepository.findSuppliersByAddressNative(address);
    }

    @Override
    public Page<SupplierDto> getSupplies(@RequestBody SupplierQueryDto supplierQueryDto) {
        return repo.getSupplies(supplierQueryDto);
    }
}
