package com.example.assessment2.dao;


import com.example.assessment2.entity.Address;
import com.example.assessment2.model.Role;
import com.example.assessment2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDAO {
    static List<User> userList = null;
    static List<Address> addresses = null;
    public UserDAO() {
        userList = new ArrayList<>();
        addresses = new ArrayList<>();
    }
    public void addUser(User user){
        userList.add(user);
    }

    public void deleteUser(User user){
        userList.remove(user);
    }

    public User getUserByIndex(int index){
        return userList.get(index);
    }

    public Role getRoleByUserIndex(int index){
        return userList.get(index).getRole();
    }

    public static List<User> getAllUsers(){
        return userList;
    }

    public static List<Address> getAllAddress(){
        return addresses;
    }
}
