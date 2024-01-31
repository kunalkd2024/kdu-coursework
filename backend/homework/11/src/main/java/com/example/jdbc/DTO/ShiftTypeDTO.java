package com.example.jdbc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ShiftTypeDTO {
    private String name;
    private String description;
    private boolean active;
    private String timeZone;
    private String tenantId;
}
