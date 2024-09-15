package org.example.entities;

public class Speaker {
    private String brand;
    private Double price;

    public Speaker(){

    }
    public Speaker(String brand, Double price){
        this.brand = brand;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
