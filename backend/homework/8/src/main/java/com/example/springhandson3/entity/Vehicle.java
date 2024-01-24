package com.example.springhandson3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Vehicle {
    private int id;
    private String name;
    private String tyre;
    private String speaker;
    private double price;
}
