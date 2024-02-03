package com.example.assessment2.entity;

import jakarta.persistence.*;
import org.springframework.security.core.parameters.P;

import java.util.UUID;

public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "User_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "products")
    private Product product;

    @Column(name = "total_value")
    private int totalValue;

}
