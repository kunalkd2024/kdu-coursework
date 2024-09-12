package org.example.q3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FactorsCalculator extends Thread {
    private final int number;
    private static final Logger logger = LoggerFactory.getLogger(org.example.q3.FactorsCalculator.class);

    public FactorsCalculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        String result = "Factors of " + number + ": ";
        logger.info(result);
        String ans = "";
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                ans = ans.concat(i + " ");
            }
        }
        logger.info(ans);
    }
}