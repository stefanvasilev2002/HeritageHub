package com.finki.heritagehub.repository;

import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndMonument(AppUser user, Monument monument);
}
