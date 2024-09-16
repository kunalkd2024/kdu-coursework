package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House, Long> {
}
