package com.kdu.smartHome.service.serviceimpl;

import com.kdu.smartHome.entity.DeviceInventory;
import com.kdu.smartHome.payload.InventoryItemDTO;
import com.kdu.smartHome.payload.InventoryItemRequestDTO;
import com.kdu.smartHome.repository.DeviceInventoryRepo;
import com.kdu.smartHome.service.InventoryService;
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

    private final ModelMapper modelMapper = new ModelMapper();

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
