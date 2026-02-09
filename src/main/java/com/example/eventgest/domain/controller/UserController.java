package com.example.eventgest.domain.controller;


import com.example.eventgest.domain.dto.UserDTO;
import com.example.eventgest.domain.service.Impl.UserServiceImpl;
import com.example.eventgest.mapper.UserMapper;
import com.example.eventgest.persistence.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> dtos = userService.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        User user = userMapper.toEntity(dto);
        User created = userService.create(user);
        return ResponseEntity.ok(userMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setId(id);
        User updated = userService.update(user);
        return ResponseEntity.ok(userMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

