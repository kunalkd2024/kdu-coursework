package org.example.services;

import org.example.entities.Tyre;
import org.springframework.context.annotation.Bean;

public class ServiceTyre {

    private ServiceTyre(){

    }
    @Bean
    public static Tyre tyre1(){
        Tyre tyre = new Tyre();
        tyre.setBrand("Bridgestone");
        tyre.setPrice(2000.0);
        return tyre;
    }

    @Bean
    public static Tyre tyre2(){
        Tyre tyre = new Tyre();
        tyre.setBrand("MRF");
        tyre.setPrice(2200.0);
        return tyre;
    }

}
