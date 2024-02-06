package com.kdu.smartHome.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequestDTO {
    private String houseName;
    private String address;

}
