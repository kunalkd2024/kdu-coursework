package com.kdu.smartHome.service.serviceimpl;

import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.entity.User;
import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.exception.custom.HouseNotFoundException;
import com.kdu.smartHome.payload.HouseRequestDTO;
import com.kdu.smartHome.payload.HouseResponseDTO;
import com.kdu.smartHome.payload.UserRegistrationRequestDTO;
import com.kdu.smartHome.repository.HouseRepo;
import com.kdu.smartHome.repository.UserRepo;
import com.kdu.smartHome.service.HouseService;
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

    private final ModelMapper modelMapper = new ModelMapper();
    String houseErrorMessage = "House not found with id: ";

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
        House house = houseRepository.findById(Long.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException(houseErrorMessage + houseId));

        User user = userRepository.findByUsername(userName);

        house.addUser(user);

        houseRepository.save(house);

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
        House house = houseRepository.findById(Long.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException(houseErrorMessage + houseId));

        house.setAddress(updatedAddress);

        House updatedHouse = houseRepository.save(house);

        return modelMapper.map(updatedHouse, HouseResponseDTO.class);
    }

    @Override
    public List<List<String>> getRoomsAndDevices(String houseId) {
        House house = houseRepository.findById(Long.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException(houseErrorMessage + houseId));

        List<List<String>> result = new ArrayList<>();

        for (Room room : house.getRooms()) {
            List<String> roomAndDevices = new ArrayList<>();
            roomAndDevices.add(room.getRoomName());

            List<String> devicesInRoom = room.getDevices().stream()
                    .map(Device::getDeviceName)
                    .toList();

            roomAndDevices.addAll(devicesInRoom);

            result.add(roomAndDevices);
        }

        return result;
    }
}
