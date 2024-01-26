package com.finki.heritagehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class AppUser {
    @Id
    private String username;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    private String confirmationToken;

    private LocalDate confirmationTokenExpiration;

    private boolean registered;
    public AppUser(){}

    public AppUser(String username, String email, String password, RoleUser role,String confirmationToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=role;
        this.confirmationToken = confirmationToken;
        this.confirmationTokenExpiration = LocalDate.now().plusDays(2);
        this.registered = false;
    }
}
