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

@Service
@EnableCaching
public class GeoService {

    private final ThirdPartyGeocodingService thirdPartyGeocodingService;

    @Autowired
    public GeoService(ThirdPartyGeocodingService thirdPartyGeocodingService) {
        this.thirdPartyGeocodingService = thirdPartyGeocodingService;
    }

    @Cacheable(value = "geocoding", key = "#requestDTO.address", unless = "#result==null || #result.address==null || #result.address.equals('Goa')")
    public ForwardGeocodingResponseDTO forwardGeocoding(ForwardGeocodingRequestDTO requestDTO) {
        GeoCodingResult result = thirdPartyGeocodingService.forwardGeocoding(requestDTO.getAddress());
        return new ForwardGeocodingResponseDTO(result.getLatitude(), result.getLongitude(), result.getAddress());
    }

    @Cacheable(value = "reverseGeocoding", key = "{#requestDTO.latitude, #requestDTO.longitude}")
    public ReverseGeocodingResponseDTO reverseGeocoding(ReverseGeocodingRequestDTO requestDTO) {
        GeoCodingResult result = thirdPartyGeocodingService.reverseGeocoding(requestDTO.getLatitude(), requestDTO.getLongitude());
        return new ReverseGeocodingResponseDTO(result.getAddress());
    }

    @CacheEvict(value = "geocoding", key = "#requestDTO.address")
    public void evictForwardGeocodingCache(ForwardGeocodingRequestDTO requestDTO) {
        thirdPartyGeocodingService.evictForwardGeocodingCache(requestDTO.getAddress());
    }

    @CacheEvict(value = "reverseGeocoding", allEntries = true)
    public void evictAllReverseGeocodingCache() {
        thirdPartyGeocodingService.evictAllReverseGeocodingCache();
    }
}
