package com.kdu.smartHome.service;

import com.kdu.smartHome.payload.HouseRequestDTO;
import com.kdu.smartHome.payload.HouseResponseDTO;
import com.kdu.smartHome.payload.UserRegistrationRequestDTO;

import java.util.List;

public interface HouseService {

    HouseResponseDTO createHouse(HouseRequestDTO houseDTO);

    UserRegistrationRequestDTO addUserToHouse(String houseId, String userName);

    List<HouseResponseDTO> getAllHouses();

    HouseResponseDTO updateHouseAddress(String houseId, String updatedAddress);

    List<List<String>> getRoomsAndDevices(String houseId);
}
