package com.example.miniproject.repository;

import com.example.miniproject.entity.House;
import com.example.miniproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Integer> {
}
