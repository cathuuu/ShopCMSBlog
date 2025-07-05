package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.CustomerDto;
import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.entites.CustomerEntity;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerMapper {
    public static CustomerDto toDto(CustomerEntity entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setUser(UserMapper.toDto(entity.getUser()));
        return dto;
    }
    public static CustomerEntity toEntity(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setUser(UserMapper.toEntity(dto.getUser(),null));
        return entity;
    }
    public static List<CustomerDto> toDtoList (List<CustomerEntity> entityList) {
        return entityList.stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }
}
