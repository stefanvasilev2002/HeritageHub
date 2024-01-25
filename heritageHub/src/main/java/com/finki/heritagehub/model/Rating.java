package com.finki.heritagehub.model;

import com.finki.heritagehub.service.MonumentFactory;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    AppUser user;
    @ManyToOne
    Monument monument;
    Double value;

    public Rating(AppUser user, Monument monument, Double value) {
        this.user = user;
        this.monument = monument;
        this.value = value;
    }

    public Rating() {

    }
}
