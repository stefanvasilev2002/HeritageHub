package com.finki.heritagehub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    private String username;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    public AppUser(){}

    public AppUser(String username, String email, String password, RoleUser role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=role;
    }
}
