package com.example.springjpa.controller;

import com.example.springjpa.entity.Shifts;
import com.example.springjpa.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PostMapping("/shift")
    public ResponseEntity<String> saveShift(@RequestBody Shifts shift) {
        shiftService.saveShift(shift);
        return ResponseEntity.ok("Shift saved successfully");
    }

    @GetMapping("/top")
    public ResponseEntity<List<Shifts>> getTop3Shifts() {
        LocalDate dateStart = LocalDate.of(2023, 1, 1);
        LocalDate dateEnd = LocalDate.of(2023, 1, 25);

        List<Shifts> top3Shifts = shiftService.findTop3ShiftsByDateRange(dateStart, dateEnd);

        return ResponseEntity.ok(top3Shifts);
    }
}