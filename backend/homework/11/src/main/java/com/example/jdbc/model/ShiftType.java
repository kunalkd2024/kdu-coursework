package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType {
    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private Timestamp createdAt;
    private String createdBy;
    private String updatedBy;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;

}
