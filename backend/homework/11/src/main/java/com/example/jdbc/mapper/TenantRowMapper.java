package com.example.jdbc.mapper;

import com.example.jdbc.model.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TenantRowMapper implements RowMapper<Tenant> {
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.fromString(rs.getString("id")));
        tenant.setName(rs.getString("username"));
        tenant.setCreatedAt(rs.getTimestamp("created_at"));
        tenant.setCreatedBy(rs.getString("created_by"));
        tenant.setUpdatedBy(rs.getString("updated_by"));
        tenant.setUpdatedAt(rs.getTimestamp("updated_at"));
        return tenant;
    }
}


