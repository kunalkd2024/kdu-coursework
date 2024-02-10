package com.example.miniproject.repository;

import com.example.miniproject.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepo extends JpaRepository<Device, Integer> {
}

