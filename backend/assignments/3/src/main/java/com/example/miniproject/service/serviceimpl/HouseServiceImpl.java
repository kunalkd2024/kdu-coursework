package com.example.miniproject.service.serviceimpl;

import com.example.miniproject.entity.House;
import com.example.miniproject.entity.Room;
import com.example.miniproject.entity.User;
import com.example.miniproject.entity.Device;
import com.example.miniproject.exception.custom.HouseNotFoundException;
import com.example.miniproject.payload.HouseRequestDTO;
import com.example.miniproject.payload.HouseResponseDTO;
import com.example.miniproject.payload.UserRegistrationRequestDTO;
import com.example.miniproject.repository.HouseRepo;
import com.example.miniproject.repository.UserRepo;
import com.example.miniproject.service.HouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    private final HouseRepo houseRepository;

    private final UserRepo userRepository;

    @Autowired
    HouseServiceImpl(HouseRepo houseRepository, UserRepo userRepository){
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    private ModelMapper modelMapper;

    @Override
    public HouseResponseDTO createHouse(HouseRequestDTO houseDTO) {
        House house = new House();
        house.setHouseName(houseDTO.getHouseName());
        house.setAddress(houseDTO.getAddress());
        houseRepository.save(house);
        return modelMapper.map(house, HouseResponseDTO.class);
    }

    @Override
    public UserRegistrationRequestDTO addUserToHouse(String houseId, String userName) {
        House house = houseRepository.findById(Integer.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + houseId));

        // Retrieve the user
        User user = userRepository.findByUsername(userName);

        // Add the user to the house
        house.addUser(user);

        // Save the updated house
        houseRepository.save(house);

        // Create and return the response DTO
        return modelMapper.map(user, UserRegistrationRequestDTO.class);
    }

    @Override
    public List<HouseResponseDTO> getAllHouses() {
        List<House> houses = houseRepository.findAll();

        return houses.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private HouseResponseDTO convertToDTO(House house) {
        return modelMapper.map(house, HouseResponseDTO.class);
    }

    @Override
    public HouseResponseDTO updateHouseAddress(String houseId, String updatedAddress) {
        House house = houseRepository.findById(Integer.valueOf(houseId))
                .orElseThrow(() -> new RuntimeException("House not found with id: " + houseId));

        house.setAddress(updatedAddress);

        House updatedHouse = houseRepository.save(house);

        return modelMapper.map(updatedHouse, HouseResponseDTO.class);
    }

    @Override
    public List<List<String>> getRoomsAndDevices(String houseId) {
        // Retrieve the house from the database
        House house = houseRepository.findById(Integer.valueOf(houseId))
                .orElseThrow(() -> new RuntimeException("House not found with id: " + houseId));

        // Prepare a list to store rooms and devices
        List<List<String>> result = new ArrayList<>();

        // Iterate through each room and fetch devices in that room
        for (Room room : house.getRooms()) {
            List<String> roomAndDevices = new ArrayList<>();
            roomAndDevices.add(room.getRoomName());

            // Fetch devices from the room
            List<String> devicesInRoom = room.getDevices().stream()
                    .map(Device::getDeviceName)
                    .toList();

            roomAndDevices.addAll(devicesInRoom);

            result.add(roomAndDevices);
        }

        return result;
    }
}
