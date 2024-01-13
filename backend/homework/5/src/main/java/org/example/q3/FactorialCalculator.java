package org.example.q3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FactorialCalculator extends Thread {
    private final int number;
    private long result;
    private static final Logger logger = LoggerFactory.getLogger(org.example.q3.FactorialCalculator.class);

    public FactorialCalculator(int number) {
        this.number = number;
    }

    public long getResult() {
        return result;
    }

    @Override
    public void run() {
        result = calculateFactorial(number);
        String ans = "Factorial of " + number + ": " + result;
        logger.info(ans);
    }

    private long calculateFactorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}