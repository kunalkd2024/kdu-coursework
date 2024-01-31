package com.example.jdbc.service;
import com.example.jdbc.DAO.ShiftTypeDAO;
import com.example.jdbc.DTO.ShiftTypeDTO;
import com.example.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class ShiftTypeService {
    @Autowired
    ShiftTypeDAO shiftTypeDAO;

    public void addShiftType(ShiftTypeDTO shiftTypeDTO){
        ShiftType shiftType = mapShiftTypeDTOToShiftType(shiftTypeDTO);
        shiftTypeDAO.saveShiftType(shiftType);
    }

    public ShiftType mapShiftTypeDTOToShiftType(ShiftTypeDTO shiftTypeDTO) {
        ShiftType shiftType = new ShiftType();
        shiftType.setId(UUID.randomUUID());
        shiftType.setName(shiftTypeDTO.getName());
        shiftType.setDescription(shiftTypeDTO.getDescription());
        shiftType.setActive(shiftTypeDTO.isActive());
        shiftType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shiftType.setTimeZone(shiftTypeDTO.getTimeZone());
        shiftType.setTenantId(UUID.fromString(shiftTypeDTO.getTenantId()));
        return shiftType;
    }

    public ShiftType getShiftTypeById(UUID id){
        return shiftTypeDAO.getShiftTypeById(id);
    }

}
