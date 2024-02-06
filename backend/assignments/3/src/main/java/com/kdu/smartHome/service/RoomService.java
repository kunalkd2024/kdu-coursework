package com.kdu.smartHome.service;

import com.kdu.smartHome.payload.RoomRequestDTO;
import com.kdu.smartHome.payload.RoomResponseDTO;

public interface RoomService {
    RoomResponseDTO addRoomToHouse(String houseId, RoomRequestDTO requestDTO);
}
