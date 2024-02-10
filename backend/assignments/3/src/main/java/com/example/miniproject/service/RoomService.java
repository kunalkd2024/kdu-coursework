package com.example.miniproject.service;

import com.example.miniproject.payload.RoomRequestDTO;
import com.example.miniproject.payload.RoomResponseDTO;

public interface RoomService {
    RoomResponseDTO addRoomToHouse(String houseId, RoomRequestDTO requestDTO);
}
