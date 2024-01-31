package com.example.spring_jdbc.service;

import com.example.spring_jdbc.dao.TenantDAO;
import com.example.spring_jdbc.dto.TenantDTO;
import com.example.spring_jdbc.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class TenantService {
    @Autowired
    TenantDAO tenantDAO;

    public void addTenant(TenantDTO tenantDTO){
        Tenant tenant = mapTenantDTOToTenant(tenantDTO);
        tenantDAO.saveTenant(tenant);
    }

    public Tenant mapTenantDTOToTenant(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.randomUUID());
        tenant.setName(tenantDTO.getName());
        tenant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setCreatedBy(tenantDTO.getCreatedBy());
        tenant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setUpdatedBy(tenantDTO.getUpdatedBy());
        return tenant;
    }


}