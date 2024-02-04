package com.example.miniproject.service;

import com.example.miniproject.entity.Device;
import com.example.miniproject.payload.DeviceAddRequestDTO;
import com.example.miniproject.payload.InventoryItemDTO;
import com.example.miniproject.payload.InventoryItemRequestDTO;

public interface DeviceService {
    public InventoryItemRequestDTO registerDevice(InventoryItemDTO requestDTO);

    public Device addDeviceToHouse(DeviceAddRequestDTO requestDTO);
}
