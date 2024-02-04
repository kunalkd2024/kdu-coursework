package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllDTO {
    private ShiftDTO shiftDTO;
    private ShiftTypeDTO  shiftTypeDTO;
    private UserDTO userDTO;
    private ShiftUserDTO shiftUserDTO;

}
