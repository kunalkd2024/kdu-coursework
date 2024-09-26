package com.example.assignment2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PositionstackReverseResponse {
    @JsonProperty("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        @JsonProperty("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
