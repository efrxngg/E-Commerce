package com.empresax.core.infrastructure.entity;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType implements GrantedAuthority {
    
    USER(Const.USER),
    ADMIN(Const.ADMIN);

    private final String authority;

    RoleType(String authority) {
        this.authority = authority;
    }

    public static RoleType fromAuthority(String authority) {
        for (RoleType rol : RoleType.values()) {
            if (rol.authority.equals(authority))
                return rol;
        }
        throw new IllegalArgumentException("Unexpeted value" + authority);
    }

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(authority);
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Const {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}
