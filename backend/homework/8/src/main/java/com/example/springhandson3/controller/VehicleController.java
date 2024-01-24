package com.example.springhandson3.controller;

import com.example.springhandson3.dto.RequestDTO;
import com.example.springhandson3.dto.ResponseDTO;
import com.example.springhandson3.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody RequestDTO vehicleRequestDTO){
        inventoryService.addVehicle(vehicleRequestDTO);
        System.out.println("Added");
        return ResponseEntity.ok("Added Successfully");
    }

    @GetMapping("/vehicle")
    public ResponseDTO searchVehicle(@RequestParam String name){
        return inventoryService.getVehicle(name);
    }

    @GetMapping("/vehicle/{id}")
    public ResponseDTO searchVehicle(@PathVariable Integer id){
        return inventoryService.getVehicle(id);
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<ResponseDTO> updateVehicle(@PathVariable int id, @RequestBody RequestDTO requestDTO) {
        ResponseDTO updatedVehicle = inventoryService.updateVehicle(id, requestDTO);
        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        boolean isDeleted = inventoryService.deleteVehicle(id);
        if (isDeleted) {
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicleByCost")
    public ResponseDTO getMostOrLeastExpensiveVehicle(@RequestParam(required = false) String sortDirection) {
        return inventoryService.getMostOrLeastExpensiveVehicle(sortDirection);
    }
}
