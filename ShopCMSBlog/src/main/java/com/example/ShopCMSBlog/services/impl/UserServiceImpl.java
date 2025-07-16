package com.example.ShopCMSBlog.services.impl;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.mappers.UserMapper;
import com.example.ShopCMSBlog.repositories.UserRepository;
import com.example.ShopCMSBlog.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends CommonServiceImpl<UserEntity, Long, UserRepository> implements UserService {


    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        super(repo);
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDto> findByIdAndUsername(Long id, String username) {
        List<UserEntity> userEntities = repo.findByIdAndUsername(id, username);
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public UserDto save(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        UserEntity userEntity = UserMapper.toEntity(userRequestDto,encodedPassword);
        validateWhenCreateOrUpdate(userRequestDto);
        UserEntity entity = repo.save(userEntity);
        return userMapper.toDto(entity);
    }


    @Override
    public void delete(Long id) {

        UserEntity userToDelete = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't fond user with this ID: " + id + ". Cann't delete!."));

        repo.delete(userToDelete);

    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {

        return repo.findByUsername(username);
    }


    private void validateWhenCreateOrUpdate(UserRequestDto  userRequestDto) {
        String roleName = userRequestDto.getUsername();
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new RuntimeException("Username is required");
        }
    }
    @Override
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return repo.findAllUsersPagedNative(pageable);
    }
    @Override
    public Page<UserEntity> searchUsersWithPagination(String username, String email, Long userId, Pageable pageable) {
        return repo.findUsersByCriteriaPaginatedNative(username, email, userId, pageable);
    }
    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return repo.findByEmailNative(email);
    }
    @Override
    public Optional<UserEntity> getUserByUsernameJpa(String username) {
        return repo.findByUsername(username);
    }
    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return repo.findByIdNative(id);
    }
}
