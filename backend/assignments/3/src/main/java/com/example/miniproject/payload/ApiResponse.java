package com.example.miniproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private String object;
    private HttpStatus httpStatus;
}