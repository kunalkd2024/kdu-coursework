package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}
