package com.example.spring_jdbc.service;

import com.example.spring_jdbc.dao.ShiftUserDAO;
import com.example.spring_jdbc.dto.ShiftUserDTO;
import com.example.spring_jdbc.entity.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftUserService {
    @Autowired
    ShiftUserDAO shiftUserDAO;

    public void addShiftUser(ShiftUserDTO shiftUserDTO){
        ShiftUser shiftUser = mapShiftUserDTOToShiftUser(shiftUserDTO);
        shiftUserDAO.saveShiftUser(shiftUser);
    }

    public ShiftUser mapShiftUserDTOToShiftUser(ShiftUserDTO shiftUserDTO) {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(UUID.randomUUID());
        shiftUser.setShiftId(UUID.fromString(shiftUserDTO.getShiftId()));
        shiftUser.setUserId(UUID.fromString(shiftUserDTO.getUserId()));
        shiftUser.setTenantId(UUID.fromString(shiftUserDTO.getTenantId()));
        return shiftUser;
    }

    public ShiftUser getShiftUserById(UUID id){
        return shiftUserDAO.getShiftUserById(id);
    }

}