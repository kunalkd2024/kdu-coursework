package com.example.miniproject.service.serviceimpl;

import com.example.miniproject.entity.DeviceInventory;
import com.example.miniproject.payload.InventoryItemDTO;
import com.example.miniproject.payload.InventoryItemRequestDTO;
import com.example.miniproject.repository.DeviceInventoryRepo;
import com.example.miniproject.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final DeviceInventoryRepo deviceInventoryRepository;

    @Autowired
    InventoryServiceImpl(DeviceInventoryRepo deviceInventoryRepository){
        this.deviceInventoryRepository = deviceInventoryRepository;
    }

    private ModelMapper modelMapper;

    public List<InventoryItemDTO> getInventory() {
        List<DeviceInventory> deviceInventories = deviceInventoryRepository.findAll();
        return deviceInventories.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private InventoryItemDTO convertToDTO(DeviceInventory deviceInventory) {
        return modelMapper.map(deviceInventory, InventoryItemDTO.class);
    }

    public InventoryItemRequestDTO addItemToInventory(InventoryItemRequestDTO requestDTO) {
        DeviceInventory deviceInventory = modelMapper.map(requestDTO, DeviceInventory.class);

        deviceInventoryRepository.save(deviceInventory);

        return modelMapper.map(deviceInventory, InventoryItemRequestDTO.class);
    }
}
