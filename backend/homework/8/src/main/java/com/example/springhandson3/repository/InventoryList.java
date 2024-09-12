package com.example.springhandson3.repository;

import com.example.springhandson3.entity.Vehicle;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class InventoryList {
    List<Vehicle> vehicleList = new ArrayList<>();
}
