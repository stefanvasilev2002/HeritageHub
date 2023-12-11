package com.finki.heritagehub.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Getter
@Setter
public class Monument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private String name;
    private boolean historic;
    private boolean cultural;
    private String city;
    public Monument() {
        // no-argument constructor for JPA
    }
}

