package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepo extends JpaRepository<Device, Long> {
}

