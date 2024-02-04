package com.example.miniproject.repository;

import com.example.miniproject.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House, Integer> {
}
