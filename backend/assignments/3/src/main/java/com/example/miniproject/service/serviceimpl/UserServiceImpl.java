package com.example.miniproject.service.serviceimpl;

import com.example.miniproject.entity.User;
import com.example.miniproject.payload.UserRegistrationRequestDTO;
import com.example.miniproject.repository.UserRepo;
import com.example.miniproject.security.JwtTokenHelper;
import com.example.miniproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    public String registerUser(UserRegistrationRequestDTO requestDTO) {
        User newUser = modelMapper.map(requestDTO, User.class);

        userRepo.save(newUser);

        return jwtTokenHelper.generateToken((UserDetails) newUser);
    }
}
