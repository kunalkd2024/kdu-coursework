package com.kdu.smartHome.entity;

import lombok.*;

import javax.persistence.*;

import javax.persistence.Entity;
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

    @Column(name = "kickston_id",unique = true)
    private String kickstonId;

    @Column(name = "device_username")
    private String deviceUsername;

    @Column(name = "device_password")
    private String devicePassword;

    @Column(name = "manufacture_date_time")
    private LocalDateTime manufactureDateTime;

    @Column(name = "manufacture_factory_place")
    private String manufactureFactoryPlace;
}