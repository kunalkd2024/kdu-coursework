package com.example.jdbc.DAO;


import com.example.jdbc.mapper.UserRowMapper;
import com.example.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {
    final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user){
        String sql = "INSERT INTO users VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getUserName(), user.isLoggedIn(), user.getTimeZone(), user.getTenantId());
    }

    public User getUserById(UUID id){
        String sql ="SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    public List<User> getUserByName(String name){
        String sql = "SELECT * FROM users WHERE username= ? ";
        return jdbcTemplate.query(sql, new UserRowMapper(), name);
    }

    public int updateNameForId(UUID id, String name){
        String sql = "UPDATE users SET userName = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, id);
    }
    public List<User> getUsers(){
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

}
