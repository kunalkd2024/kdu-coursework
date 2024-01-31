package com.example.spring_jdbc.service;

import com.example.spring_jdbc.dao.ShiftTypeDAO;
import com.example.spring_jdbc.dto.ShiftTypeDTO;
import com.example.spring_jdbc.entity.ShiftType;
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