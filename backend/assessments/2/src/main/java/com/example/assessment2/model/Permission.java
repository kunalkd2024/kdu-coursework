package com.example.assessment2.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    REGULAR_USER_READ("user:read"),
    REGULAR_USER_UPDATE("user:update"),
    REGULAR_USER_CREATE("user:create"),
    REGULAR_USER_DELETE("user:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}
