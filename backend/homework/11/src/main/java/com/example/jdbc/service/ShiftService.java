package com.example.jdbc.service;

import com.example.jdbc.DAO.ShiftDAO;
import com.example.jdbc.DTO.ShiftDTO;
import com.example.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ShiftService {

    @Autowired
    ShiftDAO shiftDAO;
    public void addShift(ShiftDTO shiftDTO){
        Shift shift = mapShiftDTOToShift(shiftDTO);

        shiftDAO.saveShift(shift);
    }

    public Shift mapShiftDTOToShift(ShiftDTO shiftDTO) {
        Shift shift = new Shift();
        shift.setId(UUID.randomUUID());
        shift.setShiftTypeId(UUID.fromString(shiftDTO.getShiftTypeId()));
        shift.setName(shiftDTO.getName());
        shift.setDateStart(LocalDate.parse(shiftDTO.getDateStart()));
        shift.setDateEnd(LocalDate.parse(shiftDTO.getDateEnd()));
        shift.setTimeStart(Time.valueOf(shiftDTO.getTimeStart()));
        shift.setTimeEnd(Time.valueOf(shiftDTO.getTimeEnd()));
        shift.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setTimeZone(shiftDTO.getTimeZone());
        shift.setTenantId(UUID.fromString(shiftDTO.getTenantId()));
        return shift;
    }

    public Shift getShiftById(UUID id){
        return shiftDAO.getShiftByid(id);
    }
}
