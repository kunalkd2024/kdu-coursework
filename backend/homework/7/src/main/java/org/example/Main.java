package org.example;

import org.example.config.Configure;
import org.example.entities.Vehicle;
import org.example.services.ServiceVehicle;
import org.example.services.VehicleFactory1;
import org.example.services.VehicleFactory2;
import org.example.util.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    @Qualifier("factory1")
    private VehicleFactory1 factory1;
    @Qualifier("factory2")
    private VehicleFactory2 factory2;
    Logging.LoggerType logger = Logging.LoggerType.INFO;
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(Configure.class);
        Main main=context.getBean(Main.class);
        main.runner();
    }

    void runner(){
        factory1.addVehicleToList();
        factory2.addVehicleToList();
        Vehicle v=factory1.findMostExpensiveVehicle();
        Vehicle v1=factory2.findMostExpensiveVehicle();
        Logging.printLogger("Most Expensive Vehicle for Factory 1:", logger);
        Logging.printLogger(v.getPrice()+" "+v.getTyre().getBrand()+" "+v.getSpeaker().getBrand(), logger);
        Logging.printLogger("Most Expensive Vehicle for Factory 2:", logger);
        Logging.printLogger(v1.getPrice()+" "+v1.getTyre().getBrand()+" "+v1.getSpeaker().getBrand(), logger);
        v=factory1.findLeastExpensiveVehicle();
        v1=factory2.findLeastExpensiveVehicle();
        Logging.printLogger("Least Expensive Vehicle for Factory 1:", logger);
        Logging.printLogger(v.getPrice()+" "+v.getTyre().getBrand()+" "+v.getSpeaker().getBrand(), logger);
        Logging.printLogger("Least Expensive Vehicle for Factory 2:", logger);
        Logging.printLogger(v1.getPrice()+" "+v1.getTyre().getBrand()+" "+v1.getSpeaker().getBrand(), logger);
    }
}

