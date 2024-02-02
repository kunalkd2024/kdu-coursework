package com.example.assessment2.controller;

import com.example.assessment2.dto.AuthenticationResponse;
import com.example.assessment2.dto.UserAuthenticationRequest;
import com.example.assessment2.dto.UserRegisterRequest;
import com.example.assessment2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRegisterRequest request
            ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody UserAuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
