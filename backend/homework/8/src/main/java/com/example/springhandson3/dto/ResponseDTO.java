package com.example.springhandson3.dto;
import com.example.springhandson3.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseDTO {
    private int id;
    private String name;
    private String tyre;
    private String speaker;
    private double price;

    public ResponseDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.speaker = vehicle.getSpeaker();
        this.price = vehicle.getPrice();
        this.tyre = vehicle.getTyre();
    }
}
