package com.kdu.smartHome.service.serviceimpl;

import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.exception.custom.HouseNotFoundException;
import com.kdu.smartHome.payload.RoomRequestDTO;
import com.kdu.smartHome.payload.RoomResponseDTO;
import com.kdu.smartHome.repository.HouseRepo;
import com.kdu.smartHome.repository.RoomRepo;
import com.kdu.smartHome.service.RoomService;
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
        House house = houseRepository.findById(Long.valueOf(houseId))
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + houseId));

        Room newRoom = new Room();
        newRoom.setRoomName(requestDTO.getRoomName());
        newRoom.setHouse(house);

        roomRepository.save(newRoom);

        return new RoomResponseDTO(
                newRoom.getId(),
                newRoom.getRoomName());
    }
}
