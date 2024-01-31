package com.example.spring_jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {
    String message;
    int statusCode;
}