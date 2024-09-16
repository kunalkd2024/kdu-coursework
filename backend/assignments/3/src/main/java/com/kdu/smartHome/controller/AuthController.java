package com.kdu.smartHome.controller;

import com.kdu.smartHome.payload.UserRegistrationRequestDTO;
import com.kdu.smartHome.payload.UserRegistrationResponseDTO;
import com.kdu.smartHome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationRequestDTO requestDTO) {
        String token = userService.registerUser(requestDTO);

        UserRegistrationResponseDTO responseDTO = new UserRegistrationResponseDTO(
                "User registered successfully",
                token);

        return ResponseEntity.ok(responseDTO);
    }
}