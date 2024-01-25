package com.finki.heritagehub.service;

import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.model.Rating;

import java.util.Optional;

public interface RatingService {
    double ratingByUserForMonument(AppUser user, Monument monument);
    Optional<Rating> getRatingByUserAndMonument(AppUser user, Monument monument);
    Rating save(Rating rating);
}
