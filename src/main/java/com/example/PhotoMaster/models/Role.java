package com.example.PhotoMaster.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
    ADMIN, CASHIER, DIRECTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
