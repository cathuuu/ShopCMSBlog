package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.RoleMapper;
import com.example.ShopCMSBlog.services.RoleService;
import com.example.ShopCMSBlog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/search")
    public ResponseEntity<Object> getUsers(@RequestParam Long id, @RequestParam String name) {
        var result = roleService.findByIdAndName(id, name);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Object> findAllUsers() {
        var result = roleService.getAll();
        List<RoleDto> roleDtos = RoleMapper.toDtoList(result);
        return ResponseEntity.ok(roleDtos);
    }

    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody RoleDto user) {
        var result = roleService.save(user);
        return ResponseEntity.ok(result);
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody RoleDto user) {
        var result = roleService.save(user);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
