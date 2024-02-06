package com.kdu.smartHome.service;

import com.kdu.smartHome.payload.UserRegistrationRequestDTO;

public interface UserService {
    String registerUser(UserRegistrationRequestDTO requestDTO);
}
