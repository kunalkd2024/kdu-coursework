package com.kdu.smartHome.exception.custom;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(String message) {
        super(message);
    }
}