package com.example.ShopCMSBlog.mappers;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class UserMapper {
    public static UserEntity toEntity(UserRequestDto userRequestDto, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.getUsername());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(password); // Mật khẩu đã được mã hóa
        return userEntity;
    }

    public static UserDto toDto(UserEntity entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setUsername(entity.getUsername());
        userDto.setEmail(entity.getEmail());
        return userDto;
    }

    public static UserEntity toEntity(UserDto dto, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(password);
        return userEntity;
    }

    public static List<UserDto> toDtoList(List<UserEntity> entityList) {
        return entityList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public UserEntity toEntityFromRequestDto(UserRequestDto dto) {
        if (dto == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId()); // Nếu có ID
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        // KHÔNG MAP MẬT KHẨU TỪ REQUEST DTO, HÃY ĐỂ SERVICE LÀM VIỆC ĐÓ SAU KHI MÃ HÓA
        return entity;
    }

    // Phương thức để cập nhật một Entity hiện có từ Request DTO
    public void updateEntityFromRequestDto(UserRequestDto dto, UserEntity entity) {
        if (dto == null || entity == null) return;
        if (dto.getUsername() != null) entity.setUsername(dto.getUsername());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        // Mật khẩu sẽ được Service xử lý riêng nếu có thay đổi
    }

    public static Optional<UserDto> toOptionalDto(Optional<UserEntity> entityOptional) {
        return entityOptional.map(UserMapper::toDto);
    }
}
