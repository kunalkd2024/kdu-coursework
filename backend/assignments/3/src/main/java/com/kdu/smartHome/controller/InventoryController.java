package com.kdu.smartHome.controller;

import com.kdu.smartHome.payload.ApiResponse;
import com.kdu.smartHome.payload.InventoryItemDTO;
import com.kdu.smartHome.payload.InventoryItemRequestDTO;
import com.kdu.smartHome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getInventory() {
        List<InventoryItemDTO> inventory = inventoryService.getInventory();
        ApiResponse apiResponse = new ApiResponse("Inventory retrieved successfully", inventory.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addItemToInventory(@RequestBody InventoryItemRequestDTO requestDTO) {
        InventoryItemRequestDTO addedItemDetails = inventoryService.addItemToInventory(requestDTO);
        ApiResponse apiResponse = new ApiResponse("Item added to inventory successfully", addedItemDetails.toString(), HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }
}