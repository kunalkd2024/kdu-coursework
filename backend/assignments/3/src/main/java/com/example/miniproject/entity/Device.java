package com.example.miniproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kickston_id")
    private String kickstonId;

    @Column(name = "device_name")
    private String deviceName;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private Long houseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long userId;

}