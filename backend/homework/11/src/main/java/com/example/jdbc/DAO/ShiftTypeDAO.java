package com.example.jdbc.DAO;


import com.example.jdbc.mapper.ShiftTypeRowMapper;
import com.example.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftTypeDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftTypeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShiftType(ShiftType shiftType){
        String sql = "INSERT INTO shifttypes VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, shiftType.getId(), shiftType.getName(), shiftType.getDescription(), shiftType.isActive(), shiftType.getCreatedAt(), shiftType.getUpdatedAt(), shiftType.getTimeZone(), shiftType.getTenantId());
    }
    public ShiftType getShiftTypeById(UUID id){
        String sql = "SELECT * FROM shifttypes WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftTypeRowMapper(), id);
    }



}

