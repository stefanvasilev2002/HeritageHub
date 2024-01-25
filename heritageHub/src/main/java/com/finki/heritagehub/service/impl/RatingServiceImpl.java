package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.model.Rating;
import com.finki.heritagehub.model.exceptions.InvalidAppUserEmailException;
import com.finki.heritagehub.repository.RatingRepository;
import com.finki.heritagehub.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public double ratingByUserForMonument(AppUser user, Monument monument) {
        return ratingRepository.findByUserAndMonument(user, monument)
                .orElseThrow(InvalidAppUserEmailException::new)
                .getValue();
    }

    @Override
    public Optional<Rating> getRatingByUserAndMonument(AppUser user, Monument monument) {
        return ratingRepository.findByUserAndMonument(user, monument);
    }

    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }
}
