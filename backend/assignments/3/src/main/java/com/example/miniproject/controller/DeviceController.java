package com.example.miniproject.controller;

import com.example.miniproject.entity.Device;
import com.example.miniproject.payload.ApiResponse;
import com.example.miniproject.payload.DeviceAddRequestDTO;
import com.example.miniproject.payload.InventoryItemDTO;
import com.example.miniproject.payload.InventoryItemRequestDTO;
import com.example.miniproject.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerDevice(@RequestBody InventoryItemDTO requestDTO) {
        InventoryItemRequestDTO registrationDetails = deviceService.registerDevice(requestDTO);
        ApiResponse apiResponse = new ApiResponse("Device registered successfully", registrationDetails.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addDeviceToHouse(
            @RequestBody DeviceAddRequestDTO requestDTO) {

        Device addedDeviceToHouse = deviceService.addDeviceToHouse(requestDTO);

        ApiResponse apiResponse = new ApiResponse(
                "Device added to the house successfully",
                addedDeviceToHouse.toString(),
                HttpStatus.OK);

        return ResponseEntity.ok(apiResponse);
    }
}
