package com.finki.heritagehub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private double rating = 0;
    private int numRatings = 0;
    public Monument() {
        // no-argument constructor for JPA
    }

    public Monument(double latitude, double longitude, String name, boolean historic, boolean cultural, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.historic = historic;
        this.cultural = cultural;
        this.city = city;
    }

    public Monument(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.historic = historic;
        this.cultural = cultural;
        this.city = city;
        this.rating = rating;
        this.numRatings = numRatings;
    }

    public Monument(Long id, double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.historic = historic;
        this.cultural = cultural;
        this.city = city;
        this.rating = rating;
        this.numRatings = numRatings;
    }
}

