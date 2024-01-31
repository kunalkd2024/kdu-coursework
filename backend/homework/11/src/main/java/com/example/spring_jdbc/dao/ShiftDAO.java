package com.example.spring_jdbc.dao;

import com.example.spring_jdbc.entity.Shift;
import com.example.spring_jdbc.mapper.ShiftRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftDAO {

    final JdbcTemplate jdbcTemplate;
    public ShiftDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShift(Shift shift){
        String sql = "INSERT INTO shift VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, shift.getId(), shift.getShiftTypeId(), shift.getName(),
                shift.getDateStart(), shift.getDateEnd(), shift.getTimeStart(), shift.getTimeEnd(),
                shift.getCreatedAt(), shift.getCreatedBy(), shift.getUpdatedBy(), shift.getUpdatedAt(), shift.getTimeZone()
                ,shift.getTenantId());
    }

    public Shift getShiftByid(UUID id){
        String sql = "SELECT * FROM shift WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftRowMapper(),id);
    }
}