package com.example.jdbc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftUserDTO {
    private String shiftId;
    private String userId;
    private String tenantId;
}
