package org.example.q2;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        TicketReservation reservation = new TicketReservation();

        logger.info("Booking flights...");
        for (String s : Arrays.asList("Booking 1: " + reservation.bookFlight("John", "Doe", 25, "Male", "economy", "C1"), "Booking 2: " + reservation.bookFlight("Jane", "Doe", 30, "Female", "business", "C2"), "\nConfirmed List:")) {
            logger.info(s);
        }

        reservation.getConfirmedList().forEach(passenger -> logger.info(passenger.toString()));

        logger.info("\nWaiting List:");
        reservation.getWaitingList().forEach(passenger -> logger.info(passenger.toString()));

        logger.info("\nCancelling flights...");
        for (String s : Arrays.asList("Cancellation 1: " + reservation.cancel("C1"), "Cancellation 2: " + reservation.cancel("C3"), "\nUpdated Confirmed List:")) {
            logger.info(s);
        }

        reservation.getConfirmedList().forEach(passenger -> logger.info(passenger.toString()));

        logger.info("\nUpdated Waiting List:");
        reservation.getWaitingList().forEach(passenger -> logger.info(passenger.toString()));
    }
}
