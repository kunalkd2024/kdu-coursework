package com.example.spring_jdbc.service;

import com.example.spring_jdbc.dao.ShiftDAO;
import com.example.spring_jdbc.dao.ShiftTypeDAO;
import com.example.spring_jdbc.dao.ShiftUserDAO;
import com.example.spring_jdbc.dao.UserDAO;
import com.example.spring_jdbc.dto.AllDTO;
import com.example.spring_jdbc.entity.Shift;
import com.example.spring_jdbc.entity.ShiftType;
import com.example.spring_jdbc.entity.ShiftUser;
import com.example.spring_jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllService {
    UserDAO userDAO;
    ShiftDAO shiftDAO;
    ShiftTypeDAO shiftTypeDAO;
    ShiftUserDAO shiftUserDAO;

    @Autowired
    AllService(UserDAO userDAO, ShiftDAO shiftDAO, ShiftTypeDAO shiftTypeDAO, ShiftUserDAO shiftUserDAO){
        this.userDAO = userDAO;
        this.shiftDAO = shiftDAO;
        this.shiftTypeDAO = shiftTypeDAO;
        this.shiftUserDAO = shiftUserDAO;
    }

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