package com.example.springjpa.controller;

import com.example.springjpa.entity.ShiftUser;
import com.example.springjpa.service.ShiftUserService;
import com.example.springjpa.exception.custom.ShiftUserNotFound;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@RestController
@RequestMapping("/api/shift-users")
public class ShiftUserController {
    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    @PostMapping
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.saveShiftUser(shiftUser);
        return ResponseEntity.ok("ShiftUser saved successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID userId) throws ShiftUserNotFound {
        shiftUserService.deleteShiftUserByShiftEndsAt(userId);
        return ResponseEntity.ok("ShiftUser deleted successfully");
    }
}