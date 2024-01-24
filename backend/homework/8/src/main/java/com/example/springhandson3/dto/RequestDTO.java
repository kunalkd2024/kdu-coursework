package com.example.springhandson3.dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RequestDTO {
    int id;
    String name;
    String tyre;
    String speaker;
     double price;
}
