package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.repository.MonumentRepository;

import java.util.List;
import java.util.Objects;

public interface MonumentService {
    List<Monument> getAllMonumentsByCategory(String category);

    Monument getMonumentById(Long id);

    void loadMonuments();

    List<Monument> getAllMonuments();

    List<Monument> getAllOrderedMonuments();

    Monument addRatingById(Long id, double rating);

    Monument save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings, Long id);

    void deleteMonument(Long id);
}
>>>>>>> Stashed changes:heritageHub/src/main/java/com/finki/heritagehub/service/MonumentService.java
