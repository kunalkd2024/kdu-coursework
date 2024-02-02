package com.example.assessment2.model;

import com.example.assessment2.model.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.assessment2.model.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    MANAGER(
            Set.of(
                    REGULAR_USER_READ,
                    REGULAR_USER_UPDATE,
                    REGULAR_USER_DELETE,
                    REGULAR_USER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    REGULAR_USER(
            Set.of(
                    REGULAR_USER_READ,
                    REGULAR_USER_UPDATE,
                    REGULAR_USER_DELETE,
                    REGULAR_USER_CREATE
            )
    )

    ;

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
