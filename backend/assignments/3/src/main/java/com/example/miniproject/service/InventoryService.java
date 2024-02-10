package com.example.miniproject.service;

import com.example.miniproject.payload.InventoryItemDTO;
import com.example.miniproject.payload.InventoryItemRequestDTO;

import java.util.List;

public interface InventoryService {
    public List<InventoryItemDTO> getInventory();

    public InventoryItemRequestDTO addItemToInventory(InventoryItemRequestDTO requestDTO);
}
