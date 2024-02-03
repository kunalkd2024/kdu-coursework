package com.example.assessment2.controller;

import com.example.assessment2.entity.Address;
import com.example.assessment2.entity.User;
import com.example.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> showUser(@RequestParam Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Integer userId) {
        userService.deleteUser(userService.getUserById(userId));
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        User olduser = userService.getUserById(userId);
        userService.deleteUser(olduser);
        userService.addUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @GetMapping("/address-all")
    public ResponseEntity<List<Address>> allAddress() {
        List<Address> addressList= userService.getAllAddress();
        return ResponseEntity.ok(addressList);
    }

    @PostMapping("/address/add")
    public ResponseEntity<String> addAddress(@RequestBody Address address) {
        userService.getAllAddress().add(address);
        return ResponseEntity.ok("Address added successfully");
    }

    @DeleteMapping("/address/delete")
    public ResponseEntity<String> deleteAddress(@RequestBody Address address) {
        userService.getAllAddress().remove(address);
        return ResponseEntity.ok("Address deleted successfully");
    }

}