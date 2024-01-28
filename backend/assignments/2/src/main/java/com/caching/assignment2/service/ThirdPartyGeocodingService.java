package com.caching.assignment2.service;

import com.caching.assignment2.entity.GeoCodingResult;
import com.caching.assignment2.exception.GeoServiceException;
import com.example.assignment2.model.PositionstackReverseResponse;
import com.caching.assignment2.util.Logging;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.web.client.RestTemplate;
import com.caching.assignment2.model.PositionstackResponse;

import java.util.concurrent.TimeUnit;

public class ThirdPartyGeocodingService {

    private final Logging logging = new Logging();
    private final String apiKey;
    private final RestTemplate restTemplate;
    private static final String POSITION_STACK_BASE_URL = "http://api.positionstack.com/v1";
    private final Cache<String, GeoCodingResult> forwardGeocodingCache;
    private final Cache<String, GeoCodingResult> reverseGeocodingCache;

    public ThirdPartyGeocodingService(String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;

        forwardGeocodingCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
        reverseGeocodingCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    public GeoCodingResult forwardGeocoding(String address) {
        GeoCodingResult cachedResult = forwardGeocodingCache.getIfPresent(address);
        if (cachedResult != null) {
            logging.logInfo("Using cached forward geocoding result for address: " + address);
            return cachedResult;
        }

        String apiUrl = String.format("%s/forward?access_key=%s&query=%s", POSITION_STACK_BASE_URL, apiKey, address);

        logging.logInfo("Sending forward geocoding request for address: " + address);

        PositionstackResponse response = restTemplate.getForObject(apiUrl, PositionstackResponse.class);

        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            PositionstackResponse.Data data = response.getData().get(0);
            double latitude = data.getLatitude();
            double longitude = data.getLongitude();
            String region = data.getRegion();

            GeoCodingResult result = new GeoCodingResult(region, latitude, longitude);

            forwardGeocodingCache.put(address, result);

            logging.logInfo("Geocoding successful for address: " + address + ". Coordinates: " + latitude + ", " + longitude);

            return result;
        } else {
            logging.logError("No geocoding result found for address: " + address);
            throw new GeoServiceException("No geocoding result found for address: " + address);
        }
    }

    public GeoCodingResult reverseGeocoding(double latitude, double longitude) {
        String cacheKey = latitude + "," + longitude;
        GeoCodingResult cachedResult = reverseGeocodingCache.getIfPresent(cacheKey);
        if (cachedResult != null) {
            logging.logInfo("Using cached reverse geocoding result for coordinates: " + latitude + ", " + longitude);
            return cachedResult;
        }

        String apiUrl = String.format("%s/reverse?access_key=%s&query=%f,%f", POSITION_STACK_BASE_URL, apiKey, latitude, longitude);

        logging.logInfo("Sending reverse geocoding request for coordinates: " + latitude + ", " + longitude);

        PositionstackReverseResponse response = restTemplate.getForObject(apiUrl, PositionstackReverseResponse.class);

        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
            PositionstackReverseResponse.Data data = response.getData().get(0);

            GeoCodingResult result = new GeoCodingResult(data.getLabel(), latitude, longitude);

            reverseGeocodingCache.put(cacheKey, result);

            logging.logInfo("Reverse geocoding successful for coordinates: " + latitude + ", " + longitude + ". Address: " + data.getLabel());

            return result;
        } else {
            logging.logError("No geocoding result found for coordinates: " + latitude + ", " + longitude);
            throw new GeoServiceException("No geocoding result found for coordinates: " + latitude + ", " + longitude);
        }
    }

    public void evictForwardGeocodingCache(String address) {
        forwardGeocodingCache.invalidate(address);
    }

    public void evictAllReverseGeocodingCache() {
        reverseGeocodingCache.invalidateAll();
    }
}
