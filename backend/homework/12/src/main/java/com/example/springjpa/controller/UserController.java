package com.example.springjpa.controller;

import com.example.springjpa.entity.User;
import com.example.springjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        // Validate page and size parameters
        if (page < 0) {
            page = 0;
        }

        if (size < 1 || size > 50) {
            size = 50;
        }

        // Create pageable object for pagination
        Pageable pageable = PageRequest.of(page, size);

        // Retrieve users from UserService with pagination
        Page<User> usersPage = userService.findAllUsers(pageable);

        return ResponseEntity.ok(usersPage);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUserDetails(userId, user);
        return ResponseEntity.ok("User details updated successfully");
    }
}