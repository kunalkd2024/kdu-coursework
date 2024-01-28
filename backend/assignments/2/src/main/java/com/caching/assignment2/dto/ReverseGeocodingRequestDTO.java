package com.caching.assignment2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReverseGeocodingRequestDTO {
    Double latitude;
    Double longitude;
}
