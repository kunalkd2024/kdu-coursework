package com.example.miniproject.service;

import com.example.miniproject.payload.UserRegistrationRequestDTO;

public interface UserService {
    String registerUser(UserRegistrationRequestDTO requestDTO);
}
