package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.mappers.SupplierMapper;
import com.example.ShopCMSBlog.services.SupplierService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlUtils.Supplier_URL)
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController( SupplierService supplierService) {

        this.supplierService = supplierService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getSupplier(@RequestParam Long id, @RequestParam String name) {
        var result = supplierService.findByIdAndName(id, name);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAllSuppliers() {
        var result = supplierService.getAll();
        List<SupplierDto> supplierDtos = SupplierMapper.toDtoList(result);
        return ResponseEntity.ok(supplierDtos);
    }

    @PostMapping()
    public ResponseEntity<Object> createSupplier(@RequestBody SupplierDto dto) {
        var result = supplierService.save(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateSupplier(@RequestBody SupplierDto dto) {
        var result = supplierService.save(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        var result = supplierService.delete(id);
        return ResponseEntity.ok(result);
    }
}
