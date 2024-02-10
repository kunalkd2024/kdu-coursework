package com.example.miniproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDTO {
    private Long id;
    private String houseName;
    private String address;

    public String toString() {
        return "House{" +
                "houseId=" + id +
                ", houseName='" + houseName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
