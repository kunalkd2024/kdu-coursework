package com.kdu.smartHome.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_name")
    private String houseName;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Device> devices;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<User> users;

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        user.setHouse(this);
    }

}