package com.example.miniproject.payload;

import lombok.Data;

@Data
public class InventoryItemRequestDTO {
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private String manufactureDateTime;
    private String manufactureFactoryPlace;
}