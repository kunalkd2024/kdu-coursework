package com.example.jdbc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String userName;
    private boolean loggedIn;
    private String timeZone;
    private UUID tenantId;

}
