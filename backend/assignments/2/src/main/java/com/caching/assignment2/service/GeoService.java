package com.caching.assignment2.service;

import com.caching.assignment2.dto.ForwardGeocodingRequestDTO;
import com.caching.assignment2.dto.ForwardGeocodingResponseDTO;
import com.caching.assignment2.dto.ReverseGeocodingRequestDTO;
import com.caching.assignment2.dto.ReverseGeocodingResponseDTO;
import com.caching.assignment2.entity.GeoCodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling geocoding and reverse geocoding requests.
 */
@Service
@EnableCaching
public class GeoService {

    private final ThirdPartyGeocodingService thirdPartyGeocodingService;

    /**
     * Constructor for GeoService.
     *
     * @param thirdPartyGeocodingService The ThirdPartyGeocodingService instance.
     */
    @Autowired
    public GeoService(ThirdPartyGeocodingService thirdPartyGeocodingService) {
        this.thirdPartyGeocodingService = thirdPartyGeocodingService;
    }

    /**
     * Retrieves geocoding information based on the provided address.
     *
     * @param requestDTO The ForwardGeocodingRequestDTO containing the address.
     * @return ForwardGeocodingResponseDTO containing latitude, longitude, and address.
     */
    @Cacheable(value = "geocoding", key = "#requestDTO.address", unless = "#result==null || #result.address==null || #result.address.equals('Goa')")
    public ForwardGeocodingResponseDTO forwardGeocoding(ForwardGeocodingRequestDTO requestDTO) {
        GeoCodingResult result = thirdPartyGeocodingService.forwardGeocoding(requestDTO.getAddress());
        return new ForwardGeocodingResponseDTO(result.getLatitude(), result.getLongitude(), result.getAddress());
    }

    /**
     * Retrieves reverse geocoding information based on the provided latitude and longitude.
     *
     * @param requestDTO The ReverseGeocodingRequestDTO containing latitude and longitude.
     * @return ReverseGeocodingResponseDTO containing the address.
     */
    @Cacheable(value = "reverseGeocoding", key = "{#requestDTO.latitude, #requestDTO.longitude}")
    public ReverseGeocodingResponseDTO reverseGeocoding(ReverseGeocodingRequestDTO requestDTO) {
        GeoCodingResult result = thirdPartyGeocodingService.reverseGeocoding(requestDTO.getLatitude(), requestDTO.getLongitude());
        return new ReverseGeocodingResponseDTO(result.getAddress());
    }

    /**
     * Evicts the cache entry for a specific forward geocoding request.
     *
     * @param requestDTO The ForwardGeocodingRequestDTO containing the address.
     */
    @CacheEvict(value = "geocoding", key = "#requestDTO.address")
    public void evictForwardGeocodingCache(ForwardGeocodingRequestDTO requestDTO) {
        thirdPartyGeocodingService.evictForwardGeocodingCache(requestDTO.getAddress());
    }

    /**
     * Evicts all entries in the reverse geocoding cache.
     */
    @CacheEvict(value = "reverseGeocoding", allEntries = true)
    public void evictAllReverseGeocodingCache() {
        thirdPartyGeocodingService.evictAllReverseGeocodingCache();
    }
}
