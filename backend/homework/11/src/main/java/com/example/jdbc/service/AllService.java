package com.example.jdbc.service;

import com.example.jdbc.DAO.ShiftDAO;
import com.example.jdbc.DAO.ShiftTypeDAO;
import com.example.jdbc.DAO.ShiftUserDAO;
import com.example.jdbc.DAO.UserDAO;
import com.example.jdbc.DTO.AllDTO;
import com.example.jdbc.model.Shift;
import com.example.jdbc.model.ShiftType;
import com.example.jdbc.model.ShiftUser;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllService {


    @Autowired
    UserDAO userDAO;
    @Autowired
    ShiftDAO shiftDAO;
    @Autowired
    ShiftTypeDAO shiftTypeDAO;
    @Autowired
    ShiftUserDAO shiftUserDAO;

    public void addAll(AllDTO allDTO) {
        UserService userService = new UserService();
        User user = userService.mapUserDTOToUser(allDTO.getUserDTO());
        ShiftTypeService shiftTypeService = new ShiftTypeService();
        ShiftType shiftType = shiftTypeService.mapShiftTypeDTOToShiftType(allDTO.getShiftTypeDTO());
        ShiftService shiftService = new ShiftService();
        Shift shift = shiftService.mapShiftDTOToShift(allDTO.getShiftDTO());
        ShiftUserService shiftUserService = new ShiftUserService();
        ShiftUser shiftUser = shiftUserService.mapShiftUserDTOToShiftUser(allDTO.getShiftUserDTO());

        userDAO.saveUser(user);
        shiftTypeDAO.saveShiftType(shiftType);
        shiftDAO.saveShift(shift);
        shiftUserDAO.saveShiftUser(shiftUser);
    }
}