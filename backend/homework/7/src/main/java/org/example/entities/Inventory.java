package org.example.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Scope("prototype")
public class Inventory {
    private List<Vehicle> vehicles;

    public Inventory(){
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle findMostExpensiveVehicle() {
        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }

    public Vehicle findLeastExpensiveVehicle() {
        return vehicles.stream()
                .min(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
    }
}
