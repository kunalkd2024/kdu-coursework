package com.example.miniproject.service;

import com.example.miniproject.entity.User;
import com.example.miniproject.payload.HouseRequestDTO;
import com.example.miniproject.payload.HouseResponseDTO;
import com.example.miniproject.payload.UserRegistrationRequestDTO;

import java.util.List;

public interface HouseService {

    HouseResponseDTO createHouse(HouseRequestDTO houseDTO);

    UserRegistrationRequestDTO addUserToHouse(String houseId, String userName);

    List<HouseResponseDTO> getAllHouses();

    HouseResponseDTO updateHouseAddress(String houseId, String updatedAddress);

    List<List<String>> getRoomsAndDevices(String houseId);
}
