package com.kdu.smartHome.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAddRequestDTO {
    private String houseId;
    private String roomId;
    private String kickstonId;
}