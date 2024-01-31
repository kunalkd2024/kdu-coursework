package com.example.spring_jdbc.dao;

import com.example.spring_jdbc.entity.ShiftUser;
import com.example.spring_jdbc.mapper.ShiftUserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftUserDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShiftUser(ShiftUser shiftUser){
        String sql= "INSERT INTO shiftuser VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, shiftUser.getId(), shiftUser.getShiftId(), shiftUser.getUserId(), shiftUser.getTenantId());
    }
    public ShiftUser getShiftUserById(UUID id){
        String  sql = "SELECT * FROM shiftuser WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftUserRowMapper(),id);
    }
}