package com.example.miniproject.repository;

import com.example.miniproject.entity.DeviceInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInventoryRepo extends JpaRepository<DeviceInventory, Long> {
    DeviceInventory findByKickstonId(String kickstonId);
}