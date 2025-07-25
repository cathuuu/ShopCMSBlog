package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.Queries.SupplierQueryDto;
import com.example.ShopCMSBlog.dtos.SupplierDto;
import com.example.ShopCMSBlog.entites.SupplierEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.SupplierMapper;
import com.example.ShopCMSBlog.services.SupplierService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlUtils.Supplier_URL)
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController( SupplierService supplierService) {

        this.supplierService = supplierService;
    }


    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<Object> findAllSuppliers() {
        var result = supplierService.getAll();
        List<SupplierDto> supplierDtos = SupplierMapper.toDtoList(result);
        return ResponseEntity.ok(supplierDtos);
    }
    @Secured("ADMIN")
    @PostMapping()
    public ResponseEntity<Object> createSupplier(@RequestBody SupplierDto dto) {
        var result = supplierService.save(dto);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @PutMapping()
    public ResponseEntity<Object> updateSupplier(@RequestBody SupplierDto dto) {
        var result = supplierService.save(dto);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<Object> deleteSupplier(@RequestParam Long id) {
        var result = supplierService.delete(id);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierEntity> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id)
                .map(supplier -> new ResponseEntity<>(supplier, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/by-address")
    public ResponseEntity<List<SupplierEntity>> getSuppliersByAddress(@RequestParam String address) {
        List<SupplierEntity> suppliers = supplierService.getSuppliersByAddress(address);
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
//    @Secured("ADMIN")
    @GetMapping("/search")
    public ResponseEntity<Object> getSupplies(@RequestBody SupplierQueryDto supplierQueryDto) {
        var result = supplierService.getSupplies(supplierQueryDto);
        return ResponseEntity.ok(result);
    }
}
