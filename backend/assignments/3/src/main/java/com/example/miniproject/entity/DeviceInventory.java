package com.example.miniproject.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_inventory")
public class DeviceInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kickstonId;

    @Column(nullable = false)
    private String deviceUsername;

    @Column(nullable = false)
    private String devicePassword;

    @Column(nullable = false)
    private LocalDateTime manufactureDateTime;

    @Column(nullable = false)
    private String manufactureFactoryPlace;
}