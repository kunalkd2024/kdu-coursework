package com.example.jdbc.mapper;

import com.example.jdbc.model.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class ShiftRowMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet result, int rowNum) throws SQLException {
        Shift shift = new Shift();
        shift.setId(UUID.fromString(result.getString("id")));
        shift.setShiftTypeId(UUID.fromString(result.getString("shiftTypeId")));
        shift.setName(result.getString("name"));
        shift.setDateStart(result.getDate("dateStart").toLocalDate());
        shift.setDateEnd(result.getDate("dateEnd").toLocalDate());
        shift.setTimeStart(result.getTime("timeStart"));
        shift.setTimeEnd(result.getTime("timeEnd"));
        shift.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setCreatedBy(result.getString("createdBy"));
        shift.setUpdatedBy(result.getString("updatedBy"));
        shift.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        shift.setTimeZone(result.getString("timeZone"));
        shift.setTenantId(UUID.fromString(result.getString("tenantId")));
  return shift;
    }

}

