package com.kdu.smartHome.service;

import com.kdu.smartHome.payload.InventoryItemDTO;
import com.kdu.smartHome.payload.InventoryItemRequestDTO;

import java.util.List;

public interface InventoryService {
    public List<InventoryItemDTO> getInventory();

    public InventoryItemRequestDTO addItemToInventory(InventoryItemRequestDTO requestDTO);
}
