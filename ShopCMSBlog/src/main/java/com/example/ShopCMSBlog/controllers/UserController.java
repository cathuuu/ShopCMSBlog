package com.example.ShopCMSBlog.controllers;

import com.example.ShopCMSBlog.dtos.UserDto;
import com.example.ShopCMSBlog.dtos.UserRequestDto;
import com.example.ShopCMSBlog.services.UserService;
import com.example.ShopCMSBlog.utils.UrlUtils;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/search")
    public ResponseEntity<Object> getUsers(@RequestParam Long id, @RequestParam String username) {
        var result = userService.findByIdAndUsername(id, username);
        return ResponseEntity.ok(result);
    }
    @GetMapping()
    public ResponseEntity<Object> findAllUsers() {
        var result = userService.getAll();
        List<UserDto> userDtos = result.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail(),user.getRoles()))  // Giả sử UserDto có constructor như vậy
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto user) {
        var result = userService.save(user);
        return ResponseEntity.ok(result);
    }
    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UserRequestDto user) {
        var result = userService.save(user);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping()
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
