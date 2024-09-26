package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.entities.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ServiceVehicle {
    private List<Vehicle> vehicles = new ArrayList<>();
    public Vehicle findMostExpensiveVehicle(){
        return vehicles.stream()
                .sorted(Comparator.comparingDouble(Vehicle::getPrice).reversed())
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    @PostConstruct
    public void addVehicleToList(){
        Vehicle vehicle1=new Vehicle(ServiceSpeaker.speaker1(),ServiceTyre.tyre1(),80000+ ServiceSpeaker.speaker1().getPrice()+ServiceTyre.tyre1().getPrice());
        vehicles.add(vehicle1);
        Vehicle vehicle2=new Vehicle(ServiceSpeaker.speaker1(),ServiceTyre.tyre2(),60000+ ServiceSpeaker.speaker1().getPrice()+ServiceTyre.tyre2().getPrice());
        vehicles.add(vehicle2);
        Vehicle vehicle3=new Vehicle(ServiceSpeaker.speaker2(),ServiceTyre.tyre1(),90000+ ServiceSpeaker.speaker2().getPrice()+ServiceTyre.tyre1().getPrice());
        vehicles.add(vehicle3);
        Vehicle vehicle4=new Vehicle(ServiceSpeaker.speaker2(),ServiceTyre.tyre2(),70000+ ServiceSpeaker.speaker2().getPrice()+ServiceTyre.tyre2().getPrice());
        vehicles.add(vehicle4);
    }

}
