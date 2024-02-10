package com.example.miniproject.service.serviceimpl;

import com.example.miniproject.entity.House;
import com.example.miniproject.entity.Room;
import com.example.miniproject.exception.custom.HouseNotFoundException;
import com.example.miniproject.payload.RoomRequestDTO;
import com.example.miniproject.payload.RoomResponseDTO;
import com.example.miniproject.repository.HouseRepo;
import com.example.miniproject.repository.RoomRepo;
import com.example.miniproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private final HouseRepo houseRepository;

    private final RoomRepo roomRepository;

    @Autowired
    RoomServiceImpl(HouseRepo houseRepository, RoomRepo roomRepository){
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
    }

    public RoomResponseDTO addRoomToHouse(String houseId, RoomRequestDTO requestDTO) {
        House house = houseRepository.findById(Integer.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + houseId));

        Room newRoom = new Room();
        newRoom.setRoomName(requestDTO.getRoomName());
        newRoom.setHouseId(house.getId());

        roomRepository.save(newRoom);

        return new RoomResponseDTO(
                newRoom.getId(),
                newRoom.getRoomName());
    }
}
