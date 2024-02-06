package com.kdu.smartHome.service.serviceimpl;

import com.kdu.smartHome.entity.User;
import com.kdu.smartHome.payload.UserRegistrationRequestDTO;
import com.kdu.smartHome.repository.UserRepo;
import com.kdu.smartHome.security.JwtTokenHelper;
import com.kdu.smartHome.security.UserDetailsServiceImpl;
import com.kdu.smartHome.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper = new ModelMapper();
    private final JwtTokenHelper jwtTokenHelper;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, JwtTokenHelper jwtTokenHelper, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    public String registerUser(UserRegistrationRequestDTO requestDTO) {
        User newUser = new User();

        newUser.setName(requestDTO.getName());
        newUser.setUsername(requestDTO.getUsername());
        newUser.setFirstName(requestDTO.getFirstName());
        newUser.setLastName(requestDTO.getLastName());
        newUser.setEmailId(requestDTO.getEmailId());
        newUser.setPassword(requestDTO.getPassword());

        userRepo.save(newUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());

        return jwtTokenHelper.generateToken(userDetails);
    }
}
