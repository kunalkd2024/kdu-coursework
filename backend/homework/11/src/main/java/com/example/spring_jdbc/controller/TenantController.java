package com.example.spring_jdbc.controller;

import com.example.spring_jdbc.dto.*;
import com.example.spring_jdbc.entity.Shift;
import com.example.spring_jdbc.entity.ShiftType;
import com.example.spring_jdbc.entity.ShiftUser;
import com.example.spring_jdbc.entity.User;
import com.example.spring_jdbc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TenantController {
    UserService userService;

    ShiftTypeService shiftTypeService;

    ShiftUserService shiftUserService;

    ShiftService shiftService;

    AllService allService;

    TenantService tenantService;

    @Autowired
    TenantController(UserService userService, ShiftTypeService shiftTypeService, ShiftUserService shiftUserService, ShiftService shiftService, AllService allService, TenantService tenantService){
        this.userService = userService;
        this.shiftService = shiftService;
        this.shiftTypeService = shiftTypeService;
        this.shiftUserService = shiftUserService;
        this.allService = allService;
        this.tenantService = tenantService;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam("name") String name) {
        try {
            List<User> users = userService.getUserByName(name);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        UUID uid = UUID.fromString(id);
        return userService.getUserById(uid);
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/users/{id}")
    public int updateUserName(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO.getUserName());
    }

    @PostMapping("/shift-types")
    public ResponseEntity<String> addShiftType(@RequestBody ShiftTypeDTO shiftType) {
        String message = "Added shift type";
        shiftTypeService.addShiftType(shiftType);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shift-types/{id}")
    public ShiftType getShiftTypeById(@PathVariable UUID id) {
        return shiftTypeService.getShiftTypeById(id);
    }

    @PostMapping("/shift-users")
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUserDTO shiftUser) {
        String message = "Add the shift user success";
        shiftUserService.addShiftUser(shiftUser);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shift-users/{id}")
    public ShiftUser getShiftUserById(@PathVariable UUID id) {
        return shiftUserService.getShiftUserById(id);
    }

    @PostMapping("/shifts")
    public ResponseEntity<String> addShift(@RequestBody ShiftDTO shift) {
        String message = "Add the shift user success";
        shiftService.addShift(shift);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/shifts/{id}")
    public Shift getShiftById(@PathVariable UUID id) {
        return shiftService.getShiftById(id);
    }

    @PostMapping("/all-values")
    public ResponseEntity<String> addAllValues(@RequestBody AllDTO allDTO) {
        allService.addAll(allDTO);
        return ResponseEntity.ok("All the values added successfully");
    }

    @PostMapping("/tenants")
    public ResponseEntity<String> addTenant(@RequestBody TenantDTO tenantDTO) {
        tenantService.addTenant(tenantDTO);
        return ResponseEntity.ok("Tenant added successfully");
    }

}