package com.caching.assignment2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForwardGeocodingResponseDTO {
    Double latitude;
    Double longitude;
    String address;
}
