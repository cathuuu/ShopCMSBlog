package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.entites.UserEntity;
import com.example.ShopCMSBlog.services.UserService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(UrlUtils.USER_URL)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Secured("ADMIN")
    @GetMapping("/search")
    public ResponseEntity<Object> getUsers(@RequestParam Long id, @RequestParam String username) {
        var result = userService.findByIdAndUsername(id, username);
        return ResponseEntity.ok(result);
    }
    @GetMapping()
    @Secured("ADMIN")
    public ResponseEntity<Page<UserEntity>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserEntity> usersPage = userService.getAllUsers(pageable);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }
    @PostMapping()
    @Secured("ADMIN")
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto user) {
        var result = userService.save(user);
        return ResponseEntity.ok(result);
    }
    @PutMapping()
    @Secured("ADMIN")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequestDto user) {
        var result = userService.save(user);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping()
    @Secured("ADMIN")
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Secured("ADMIN")
    @GetMapping("/search-paginated")
    public ResponseEntity<Page<UserEntity>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserEntity> usersPage = userService.searchUsersWithPagination(username, email, userId, pageable);
        return ResponseEntity.ok(usersPage);
    }

    @Secured("ADMIN")
    // API lấy một người dùng DUY NHẤT theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
