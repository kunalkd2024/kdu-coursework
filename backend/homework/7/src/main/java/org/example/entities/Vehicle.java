package org.example.entities;

public class Vehicle {
    private Speaker speaker;
    private Tyre tyre;
    private Double price;

    public Vehicle(Speaker speaker, Tyre tyre, Double price){
        this.speaker = speaker;
        this.tyre = tyre;
        this.price = getVehiclePrice(price);
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVehiclePrice(Double price){
        return speaker.getPrice() + tyre.getPrice() + price;
    }
}
