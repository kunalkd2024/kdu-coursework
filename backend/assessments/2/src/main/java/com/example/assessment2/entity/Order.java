package com.example.assessment2.entity;

//Each order should have details like order date, total amount, shipping address,
//etc.

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private int totalAmount;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany
    @JoinColumn(name = "product")
    private Product product;
}
