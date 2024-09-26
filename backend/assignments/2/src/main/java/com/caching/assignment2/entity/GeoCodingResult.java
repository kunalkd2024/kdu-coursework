package com.caching.assignment2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoCodingResult {
    private String address;
    private Double latitude;
    private Double longitude;
}
