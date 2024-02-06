package com.kdu.smartHome.entity;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Device> devices;
}