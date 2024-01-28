package com.caching.assignment2.controller;

import com.caching.assignment2.exception.GeoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.caching.assignment2.dto.ForwardGeocodingRequestDTO;
import com.caching.assignment2.dto.ReverseGeocodingRequestDTO;
import com.caching.assignment2.dto.ForwardGeocodingResponseDTO;
import com.caching.assignment2.dto.ReverseGeocodingResponseDTO;
import com.caching.assignment2.service.GeoService;

@RestController
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/geocoding")
    public ResponseEntity<ForwardGeocodingResponseDTO> forwardGeocoding(@RequestParam String address){
        try {
            if (address == null || address.isBlank()) {
                throw new GeoServiceException("address is blank");
            }
            ForwardGeocodingRequestDTO requestDTO = new ForwardGeocodingRequestDTO(address);
            ForwardGeocodingResponseDTO responseDTO = geoService.forwardGeocoding(requestDTO);
            if (responseDTO == null) {
                throw new GeoServiceException("null data fetched");
            }
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            throw new GeoServiceException("error found");
        }
    }

    @GetMapping("/reverse-geocoding")
    public String reverseGeocoding(@RequestParam("latitude") String latitude,
                                   @RequestParam("longitude") String longitude) {
        double lat = 0.0;
        double lon = 0.0;
        try {
            lat = Double.parseDouble(latitude);
            lon = Double.parseDouble(longitude);
        }
        catch (NullPointerException | NumberFormatException e){
            throw new GeoServiceException(e.getMessage());
        }
        ReverseGeocodingRequestDTO requestDTO = new ReverseGeocodingRequestDTO(lat, lon);
        ReverseGeocodingResponseDTO responseDTO = geoService.reverseGeocoding(requestDTO);
        return responseDTO.getAddress();
    }

}
