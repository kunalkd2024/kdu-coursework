package com.example.assessment2.config;

import com.example.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonUserDetails implements UserDetailsService {
    UserService userService;

    @Autowired
    public PersonUserDetails(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.assessment2.entity.User user = userService.getPersonUsername(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;
        if(user == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register fist.");
        } else {
            personUserName = user.getUsername();
            personPassword = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        }

        return new User(personUserName,personPassword,authorities);
    }
}
