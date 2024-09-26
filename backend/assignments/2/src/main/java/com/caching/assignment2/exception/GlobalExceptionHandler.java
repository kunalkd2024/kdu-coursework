package com.caching.assignment2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for handling exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles specific exceptions of type GeoServiceException.
     * @param ex The GeoServiceException instance.
     * @return ResponseEntity with a BAD_REQUEST status and the exception message.
     */
    @ExceptionHandler(GeoServiceException.class)
    public ResponseEntity<String> handleGeoServiceException(GeoServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles general exceptions.
     * @param ex The Exception instance.
     * @return ResponseEntity with a BAD_REQUEST status and a generic error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An unexpected internal server error occurred: \n" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
