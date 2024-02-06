package com.kdu.smartHome.service;

import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.payload.DeviceAddRequestDTO;
import com.kdu.smartHome.payload.InventoryItemDTO;
import com.kdu.smartHome.payload.InventoryItemRequestDTO;

public interface DeviceService {
    public InventoryItemRequestDTO registerDevice(InventoryItemDTO requestDTO);

    public Device addDeviceToHouse(DeviceAddRequestDTO requestDTO);
}
