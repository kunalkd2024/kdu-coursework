package org.example;

import org.example.config.Configure;
import org.example.services.ServiceVehicle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configure.class);
        ServiceVehicle serviceVehicle = context.getBean(ServiceVehicle.class);
        System.out.println("The price for the most expensive vehicle is " + serviceVehicle.findMostExpensiveVehicle().getPrice());
    }
}

