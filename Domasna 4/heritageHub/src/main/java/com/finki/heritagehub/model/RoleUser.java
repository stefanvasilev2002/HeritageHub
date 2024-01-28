package com.finki.heritagehub.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_CONFIRMED;
    @Override
    public String getAuthority() {
        return name();
    }
}
