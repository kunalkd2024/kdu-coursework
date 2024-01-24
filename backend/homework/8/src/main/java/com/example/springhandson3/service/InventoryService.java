package com.example.springhandson3.service;

import com.example.springhandson3.dto.RequestDTO;
import com.example.springhandson3.dto.ResponseDTO;
import com.example.springhandson3.entity.Vehicle;
import com.example.springhandson3.repository.InventoryList;
import com.example.springhandson3.util.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;

@Service
public class InventoryService {
    Logging logger = new Logging();
    @Autowired
    InventoryList inventoryList;

    public void addVehicle(RequestDTO requestDTO){
        int id = requestDTO.getId();
        String name = requestDTO.getName();
        String tyre = requestDTO.getTyre();
        String speaker = requestDTO.getSpeaker();
        double price = requestDTO.getPrice();
        Vehicle vehicle = new Vehicle(id, name, tyre, speaker, price);
        inventoryList.getVehicleList().add(vehicle);
        logger.logInfo("Vehicle Added with id: "+ vehicle.getId() +" Name: "+vehicle.getName());
    }

    public ResponseDTO getVehicle(String name){
        for(var vehicle : inventoryList.getVehicleList()){
            if(vehicle.getName().equals(name)){
                int id = vehicle.getId();
                String tyre = vehicle.getTyre();
                String speaker = vehicle.getSpeaker();
                double price = vehicle.getPrice();
                logger.logInfo("Vehicle returned with Name: "+vehicle.getName());
                return new ResponseDTO(id, name, tyre, speaker, price);
            }
        }
        return null;
    }

    public ResponseDTO getVehicle(int id){
        for(var vehicle : inventoryList.getVehicleList()){
            if(vehicle.getId() == id){
                String name = vehicle.getName();
                String tyre = vehicle.getTyre();
                String speaker = vehicle.getSpeaker();
                double price = vehicle.getPrice();
                logger.logInfo("Vehicle returned with id: "+ vehicle.getId());
                return new ResponseDTO(id, name, tyre, speaker, price);
            }
        }
        return null;
    }

    public ResponseDTO updateVehicle(int id, RequestDTO vehicleRequestDTO){
        for(var vehicle : inventoryList.getVehicleList()){
            if(vehicle.getId() == id){
                vehicle.setName(vehicleRequestDTO.getName());
                vehicle.setSpeaker(vehicleRequestDTO.getSpeaker());
                vehicle.setTyre(vehicleRequestDTO.getTyre());
                vehicle.setPrice(vehicleRequestDTO.getPrice());
                logger.logInfo("Vehicle updated with id: "+ vehicle.getId());
                return new ResponseDTO(vehicle.getId(), vehicle.getName(), vehicle.getTyre(), vehicle.getSpeaker(), vehicle.getPrice());
            }
        }
        return null;
    }

    public boolean deleteVehicle(int id) {
        Iterator<Vehicle> iterator = inventoryList.getVehicleList().iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId() == id) {
                iterator.remove();
                logger.logInfo("Vehicle found with id: "+ vehicle.getId());
                return true;
            }
        }
        logger.logInfo("Vehicle not found.");
        return false;
    }

    public ResponseDTO getMostOrLeastExpensiveVehicle(String sortDirection) {
        Vehicle vehicle = null;
        if (sortDirection.equals("asc")) {
            vehicle = inventoryList.getVehicleList().stream().max(Comparator.comparingDouble(Vehicle::getPrice)).get();
        } else if (sortDirection.equals("desc")) {
            vehicle = inventoryList.getVehicleList().stream().min(Comparator.comparingDouble(Vehicle::getPrice)).get();
        }
        return new ResponseDTO(vehicle);
    }

}
