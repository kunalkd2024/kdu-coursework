package com.example.jdbc.DAO;

import com.example.jdbc.mapper.TenantRowMapper;
import com.example.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDAO {
    final JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTenant(Tenant tenant) {
        String sql = "INSERT INTO tenant VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, tenant.getId(), tenant.getName(), tenant.getCreatedAt(), tenant.getCreatedBy(), tenant.getUpdatedAt(), tenant.getUpdatedBy());
    }

    public Tenant getTenantById(int id) {
        String sql = "SELECT * FROM tenant WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new TenantRowMapper(), id);
    }

    public int updateNameForId(int id, String name) {
        String sql = "UPDATE tenant SET name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }
}
