package org.example.services;

import org.example.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ServiceSpeaker {

    private ServiceSpeaker(){

    }
    @Bean
    public static Speaker speaker1(){
        Speaker speaker = new Speaker();
        speaker.setBrand("Sony");
        speaker.setPrice(20000.0);
        return speaker;
    }

    @Bean
    public static Speaker speaker2(){
        Speaker speaker = new Speaker();
        speaker.setBrand("Bose");
        speaker.setPrice(22000.0);
        return speaker;
    }
}
