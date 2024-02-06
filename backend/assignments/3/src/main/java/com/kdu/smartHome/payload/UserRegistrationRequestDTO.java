package com.kdu.smartHome.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequestDTO {
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private String emailId;

    @Override
    public String toString() {
        return "UserRegistrationRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}