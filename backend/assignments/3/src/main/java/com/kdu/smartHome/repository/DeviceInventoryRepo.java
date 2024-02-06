package com.kdu.smartHome.repository;

import com.kdu.smartHome.entity.DeviceInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInventoryRepo extends JpaRepository<DeviceInventory, Long> {
    DeviceInventory findByKickstonId(String kickstonId);
}