package com.example.miniproject.controller;

import com.example.miniproject.payload.ApiResponse;
import com.example.miniproject.payload.UserRegistrationRequestDTO;
import com.example.miniproject.payload.UserRegistrationResponseDTO;
import com.example.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationRequestDTO requestDTO) {
        String token = userService.registerUser(requestDTO);

        UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO(
                "User registered successfully",
                token);

        return ResponseEntity.ok(responseDTO);
    }
}