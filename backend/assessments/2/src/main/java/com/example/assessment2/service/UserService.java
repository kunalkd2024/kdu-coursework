package com.example.assessment2.service;

import com.example.assessment2.entity.Address;
import com.example.assessment2.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assessment2.dao.UserDAO;
import com.example.assessment2.entity.User;

import java.util.List;

@Service
public class UserService {
    UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public void addUser(User user){
        userDAO.addUser(user);
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public List<Address> getAllAddress(){
        return UserDAO.getAllAddress();
    }

    public User getUserById(int id){
        return userDAO.getUserByIndex(id);
    }

    public Role getRoleById(int id){
        return userDAO.getRoleByUserIndex(id);
    }
    public User getPersonUsername(String name){
        for(User u : UserDAO.getAllUsers()){
            if(u.getUsername().equals(name)){
                return u;
            }
        }
        return null;
    }
}
