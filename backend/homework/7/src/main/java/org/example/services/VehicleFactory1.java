package org.example.services;

import org.example.entities.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("factoryOne")
@Scope("prototype")
public class VehicleFactory1 extends ServiceVehicle{
    @Autowired
    public VehicleFactory1(ServiceTyre serviceTyre, ServiceSpeaker serviceSpeaker,Inventory inventory) {
        super(serviceTyre,serviceSpeaker,inventory);
    }
    public double calculateRandomPrice() {
        return Math.random() * 40000 + 60000;
    }
}
