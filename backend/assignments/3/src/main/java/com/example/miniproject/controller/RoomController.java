package com.example.miniproject.controller;

import com.example.miniproject.payload.ApiResponse;
import com.example.miniproject.payload.RoomRequestDTO;
import com.example.miniproject.payload.RoomResponseDTO;
import com.example.miniproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addRoomToHouse(
            @RequestParam("houseId") String houseId,
            @RequestBody RoomRequestDTO requestDTO) {

        RoomResponseDTO responseDTO = roomService.addRoomToHouse(houseId, requestDTO);
        ApiResponse apiResponse = new ApiResponse("House added successfully", responseDTO.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }
}