package com.kdu.smartHome.controller;

import com.kdu.smartHome.payload.ApiResponse;
import com.kdu.smartHome.payload.UserRegistrationRequestDTO;
import com.kdu.smartHome.service.HouseService;
import com.kdu.smartHome.payload.HouseRequestDTO;
import com.kdu.smartHome.payload.HouseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createHouse(@RequestBody HouseRequestDTO houseDTO){
        HouseResponseDTO createdHouse = houseService.createHouse(houseDTO);
        ApiResponse apiResponse = new ApiResponse("House added successfully", createdHouse.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<ApiResponse> addUser(@PathVariable("houseId") String houseId,
                                               @RequestBody String userName){
        UserRegistrationRequestDTO addedUser = houseService.addUserToHouse(houseId, userName);

        ApiResponse apiResponse = new ApiResponse("User added to the house successfully", addedUser.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getHouses() {
        List<HouseResponseDTO> houses = houseService.getAllHouses();

        ApiResponse apiResponse = new ApiResponse("List of houses retrieved successfully", houses.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> updateHouseAddress(
            @RequestParam("houseId") String houseId,
            @RequestBody String updatedAddress) {

        HouseResponseDTO updatedHouse = houseService.updateHouseAddress(houseId, updatedAddress);

        ApiResponse apiResponse = new ApiResponse("House address updated successfully", updatedHouse.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ApiResponse> getRoomsAndDevices(@PathVariable("houseId") String houseId) {

        List<List<String>> roomsAndDevices = houseService.getRoomsAndDevices(houseId);

        ApiResponse apiResponse = new ApiResponse("Rooms and devices retrieved successfully", roomsAndDevices.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }
}
