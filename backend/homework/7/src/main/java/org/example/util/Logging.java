package org.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {
    public enum LoggerType {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);
    public static void printLogger(String message, LoggerType type) {
        switch (type) {
            case INFO -> logger.info(message);
            case DEBUG -> logger.debug(message);
            case WARNING -> logger.warn(message);
            case ERROR -> logger.error(message);
        }
    }
}