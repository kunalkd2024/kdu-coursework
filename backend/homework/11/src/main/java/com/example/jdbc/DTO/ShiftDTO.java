package com.example.jdbc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
@Data
@AllArgsConstructor
public class ShiftDTO {
    private String shiftTypeId;
    private String name;
    private String dateStart;
    private String dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String timeZone;
    private String tenantId;
}
