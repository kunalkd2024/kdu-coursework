package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    private UUID id;
    private UUID shiftTypeId;
    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private Timestamp createdAt;
    private String createdBy;
    private String updatedBy;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;

}
