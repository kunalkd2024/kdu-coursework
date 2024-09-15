package org.example.services;

import org.example.entities.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("factoryTwo")
@Scope("prototype")
public class VehicleFactory2 extends ServiceVehicle{
    @Autowired
    public VehicleFactory2(ServiceTyre serviceTyre, ServiceSpeaker serviceSpeaker, Inventory inventory) {
        super(serviceTyre,serviceSpeaker,inventory);
    }
    public double calculateRandomPrice() {
        return Math.random() * 50000 + 40000;
    }
}