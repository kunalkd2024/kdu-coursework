package org.example.q3;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(org.example.q3.Main.class.getName());
    /**
     * Main method for swapping elements at specified indices in an Integer array.
     * It uses a Scanner for user input and logs the original and modified arrays.
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Integer[] array = {1, 2, 3, 4, 5};

        logger.info("Original Array :");
        printArr(array);
        logger.info("Enter the first index :");
        int firstIndex = scanner.nextInt();
        logger.info("Enter the second index :");
        int secondIndex = scanner.nextInt();
        Exchange exchange = new Exchange();
        exchange.swap(array, firstIndex, secondIndex);
        logger.info("Modified Array :");
        printArr(array);
    }

    public static <T> void printArr(T[] array){
        for(T element : array){
            logger.info("%s ".formatted(element));
        }
    }
}
