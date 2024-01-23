package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.entities.Inventory;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public abstract class ServiceVehicle {
    private ServiceTyre serviceTyre;
    private ServiceSpeaker serviceSpeaker;
    protected Inventory inventory;
    @Autowired
    protected ServiceVehicle(ServiceTyre serviceTyre, ServiceSpeaker serviceSpeaker,Inventory inventory) {
        this.serviceTyre = serviceTyre;
        this.serviceSpeaker = serviceSpeaker;
        this.inventory = inventory;
    }
    public Inventory getInventory() {
        return inventory;
    }
    private final List<Vehicle> vehicles = new ArrayList<>();
    public Vehicle findMostExpensiveVehicle(){
        return vehicles.stream()
                .sorted(Comparator.comparingDouble(Vehicle::getPrice).reversed())
                .limit(1)
                .findFirst()
                .orElse(null);
    }

    public Vehicle findLeastExpensiveVehicle(){
        return vehicles.stream()
                .sorted(Comparator.comparingDouble(Vehicle::getPrice))
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
    public abstract double calculateRandomPrice();
}
