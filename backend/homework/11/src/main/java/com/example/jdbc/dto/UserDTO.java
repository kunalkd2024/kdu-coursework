package com.example.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userName;
    private boolean loggedIn;
    private String timeZone;
    private String tenantId;

}
