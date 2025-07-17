package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.RoleDto;
import com.example.ShopCMSBlog.entites.RoleEntity;
import com.example.ShopCMSBlog.enums.RoleEnum;
import com.example.ShopCMSBlog.mappers.RoleMapper;
import com.example.ShopCMSBlog.repositories.RoleRepository;
import com.example.ShopCMSBlog.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl extends CommonServiceImpl<RoleEntity, Long, RoleRepository> implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository repo, RoleMapper roleMapper) {
        super(repo);
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> findByIdAndName(Long id, RoleEnum name) {
        return repo.findByIdAndName(id, name);
    }

    @Override
    public RoleDto delete(Long id) {
        RoleEntity roleEntity = repo.findById(id).orElseThrow(() ->
                new RuntimeException("cann't find role with id: " + id));
        repo.delete(roleEntity);
        return RoleMapper.toDto(roleEntity); // Trả về đối tượng đã xóa
    }

    @Override
    public Optional<RoleEntity> findByName(RoleEnum  name) {
        return repo.findByName(name);
    }

    @Override
    public List<RoleEntity> findAllByIdIn(List<Long> roleIds) {
        return repo.findAllById(roleIds);
    }

    @Override
    public RoleDto save(RoleDto dto) {
        //validateWhenCreateOrUpdate(dto);
        RoleEntity roleEntity = RoleMapper.toEntity(dto);
        roleEntity = repo.save(roleEntity);
        return RoleMapper.toDto(roleEntity);
    }

//    private void validateWhenCreateOrUpdate(RoleDto dto) {
//        String roleName = dto.getName();
//        if (roleName == null || roleName.trim().isEmpty()) {
//            throw new RuntimeException("Customer name is required");
//        }
//    }
}
