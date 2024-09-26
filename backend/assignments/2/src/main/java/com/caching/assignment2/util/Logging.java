package com.caching.assignment2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    public void logInfo(String msg) {
        logger.info(msg);
    }

    public void logDebug(String msg) {
        logger.debug(msg);
    }

    public void logError(String msg) {
        logger.error(msg);
    }

}