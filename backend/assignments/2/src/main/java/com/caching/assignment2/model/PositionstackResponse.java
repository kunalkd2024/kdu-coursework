package com.caching.assignment2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PositionstackResponse {

    @JsonProperty("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private double latitude;
        private double longitude;
        private String region;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}