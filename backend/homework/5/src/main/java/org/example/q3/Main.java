package org.example.q3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents the main application for concurrent calculation of factorial
 * and factors of a number. It initializes threads for FactorialCalculator and
 * FactorsCalculator, starts them concurrently, and waits for their completion.
 */
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