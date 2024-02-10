package com.example.miniproject.service.serviceimpl;

import com.example.miniproject.entity.Device;
import com.example.miniproject.entity.DeviceInventory;
import com.example.miniproject.exception.custom.DeviceNotFoundException;
import com.example.miniproject.exception.custom.HouseNotFoundException;
import com.example.miniproject.exception.custom.RoomNotFoundException;
import com.example.miniproject.payload.DeviceAddRequestDTO;
import com.example.miniproject.payload.InventoryItemDTO;
import com.example.miniproject.payload.InventoryItemRequestDTO;
import com.example.miniproject.repository.DeviceInventoryRepo;
import com.example.miniproject.repository.DeviceRepo;
import com.example.miniproject.repository.HouseRepo;
import com.example.miniproject.repository.RoomRepo;
import com.example.miniproject.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final HouseRepo houseRepository;

    private final RoomRepo roomRepository;

    private final DeviceInventoryRepo deviceInventoryRepository;

    private final DeviceRepo deviceRepository;

    @Autowired
    DeviceServiceImpl(HouseRepo houseRepository, RoomRepo roomRepository, DeviceInventoryRepo deviceInventoryRepository, DeviceRepo deviceRepository){
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.deviceInventoryRepository = deviceInventoryRepository;
        this.deviceRepository = deviceRepository;
    }

    private ModelMapper modelMapper;

    public InventoryItemRequestDTO registerDevice(InventoryItemDTO inventoryItemDTO) {
        DeviceInventory existingDevice = deviceInventoryRepository.findByKickstonId(inventoryItemDTO.getKickstonId());
        if (existingDevice != null) {
            return modelMapper.map(existingDevice, InventoryItemRequestDTO.class);
        }

        DeviceInventory deviceInventory = modelMapper.map(inventoryItemDTO, DeviceInventory.class);

        DeviceInventory requestDTO = deviceInventoryRepository.save(deviceInventory);

        return modelMapper.map(requestDTO, InventoryItemRequestDTO.class);
    }

    public Device addDeviceToHouse(DeviceAddRequestDTO requestDTO) {
        try {
            houseRepository.existsById(Integer.valueOf(requestDTO.getHouseId()));
        }catch (HouseNotFoundException e){
            throw new HouseNotFoundException("House not found with id: " + requestDTO.getHouseId());
        }
        try {
            roomRepository.existsById(Integer.valueOf(requestDTO.getRoomId()));
        }catch (RoomNotFoundException e){
            throw new RoomNotFoundException("Room not found with id: " + requestDTO.getRoomId());
        }

        DeviceInventory device = deviceInventoryRepository.findById(Long.valueOf(requestDTO.getKickstonId()))
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + requestDTO.getKickstonId()));

        Device addedDevice = modelMapper.map(device, Device.class);
        addedDevice.setHouseId(Long.valueOf(requestDTO.getHouseId()));
        addedDevice.setRoomId(Long.valueOf(requestDTO.getRoomId()));

        deviceRepository.save(addedDevice);

        return addedDevice;
    }
}
