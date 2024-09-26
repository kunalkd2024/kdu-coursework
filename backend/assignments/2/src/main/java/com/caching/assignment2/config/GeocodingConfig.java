package com.caching.assignment2.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.caching.assignment2.service.ThirdPartyGeocodingService;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for Geocoding-related beans and caching setup.
 */
@Configuration
@EnableCaching
public class GeocodingConfig {

    @Value("${api-key}")
    private String geocodingApiKey;

    /**
     * Provides a configured instance of RestTemplate.
     *
     * @return Configured RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Provides a ThirdPartyGeocodingService instance with the necessary dependencies.
     *
     * @param restTemplate RestTemplate for making HTTP requests.
     * @return Configured ThirdPartyGeocodingService instance.
     */
    @Bean
    public ThirdPartyGeocodingService thirdPartyGeocodingService(RestTemplate restTemplate) {
        return new ThirdPartyGeocodingService(geocodingApiKey, restTemplate);
    }

    /**
     * Configures and provides a Caffeine-based CacheManager for caching geocoding data.
     *
     * @return Configured CacheManager instance.
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000));
        return cacheManager;
    }
}
