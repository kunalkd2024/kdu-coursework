package com.kdu.smartHome.service.serviceimpl;

import com.kdu.smartHome.entity.Device;
import com.kdu.smartHome.entity.DeviceInventory;
import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.Room;
import com.kdu.smartHome.exception.custom.DeviceNotFoundException;
import com.kdu.smartHome.exception.custom.HouseNotFoundException;
import com.kdu.smartHome.exception.custom.RoomNotFoundException;
import com.kdu.smartHome.payload.DeviceAddRequestDTO;
import com.kdu.smartHome.payload.InventoryItemDTO;
import com.kdu.smartHome.payload.InventoryItemRequestDTO;
import com.kdu.smartHome.repository.DeviceInventoryRepo;
import com.kdu.smartHome.repository.DeviceRepo;
import com.kdu.smartHome.repository.HouseRepo;
import com.kdu.smartHome.repository.RoomRepo;
import com.kdu.smartHome.service.DeviceService;
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

    private final ModelMapper modelMapper = new ModelMapper();

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
        House house = houseRepository.findById(Long.valueOf(requestDTO.getHouseId()))
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + requestDTO.getHouseId()));
        Room room = roomRepository.findById(Long.valueOf(requestDTO.getRoomId()))
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + requestDTO.getRoomId()));
        DeviceInventory device = deviceInventoryRepository.findById(Long.valueOf(requestDTO.getKickstonId()))
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + requestDTO.getKickstonId()));

        Device addedDevice = modelMapper.map(device, Device.class);
        addedDevice.setHouse(house);
        addedDevice.setRoom(room);

        deviceRepository.save(addedDevice);

        return addedDevice;
    }
}
