package org.example.q3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(org.example.q3.Main.class);
    public static void main(String[] args) {
        int number = 6;

        FactorialCalculator factorialThread = new FactorialCalculator(number);
        FactorsCalculator factorsThread = new FactorsCalculator(number);

        factorialThread.start();
        factorsThread.start();

        try {
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        logger.info("Main thread finished last.");
    }
}