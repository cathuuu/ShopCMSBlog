package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.enums.RoleEnum;
import com.example.ShopCMSBlog.mappers.RoleMapper;
import com.example.ShopCMSBlog.services.RoleService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlUtils.Role_URL)
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Secured("ADMIN")
    @GetMapping("/search")
    public ResponseEntity<Object> findRole(@RequestParam Long id, @RequestParam RoleEnum name) {
        var result = roleService.findByIdAndName(id, name);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @GetMapping()
    public ResponseEntity<Object> findAllUsers() {
        var result = roleService.getAll();
        List<RoleDto> roleDtos = RoleMapper.toDtoList(result);
        return ResponseEntity.ok(roleDtos);
    }
    @Secured("ADMIN")
    @PostMapping()
    public ResponseEntity<Object> createRole(@RequestBody RoleDto role) {
        var result = roleService.save(role);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @PutMapping()
    public ResponseEntity<Object> updateRole(@RequestBody RoleDto role) {
        var result = roleService.save(role);
        return ResponseEntity.ok(result);
    }
    @Secured("ADMIN")
    @DeleteMapping()
    public ResponseEntity<Object> deleteRole(@RequestParam Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
