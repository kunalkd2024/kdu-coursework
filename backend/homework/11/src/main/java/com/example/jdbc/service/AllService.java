package com.example.jdbc.service;

import com.example.jdbc.dao.ShiftDAO;
import com.example.jdbc.dao.ShiftTypeDAO;
import com.example.jdbc.dao.ShiftUserDAO;
import com.example.jdbc.dao.UserDAO;
import com.example.jdbc.dto.AllDTO;
import com.example.jdbc.model.Shift;
import com.example.jdbc.model.ShiftType;
import com.example.jdbc.model.ShiftUser;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllService {
    private final UserDAO userDAO;
    private final ShiftDAO shiftDAO;
    private final ShiftTypeDAO shiftTypeDAO;
    private final ShiftUserDAO shiftUserDAO;

    @Autowired
    public AllService(
            UserDAO userDAO,
            ShiftDAO shiftDAO,
            ShiftTypeDAO shiftTypeDAO,
            ShiftUserDAO shiftUserDAO) {
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