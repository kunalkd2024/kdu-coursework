package com.caching.assignment2.exception;

/**
 * Custom runtime exception class for handling exceptions related to the GeoService.
 */
public class GeoServiceException extends RuntimeException {
    public GeoServiceException(String message) {
        super(message);
    }
}