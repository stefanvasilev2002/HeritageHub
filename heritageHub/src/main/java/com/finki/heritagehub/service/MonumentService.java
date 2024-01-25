package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;

import java.util.List;

public interface MonumentService {
    List<Monument> getAllMonumentsByCategory(String category);

    Monument getMonumentById(Long id);

    List<Monument> getAllMonuments();

    List<Monument> getAllOrderedMonuments();

    void addRatingById(Long id, double rating);

    void save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city);

    void deleteMonument(Long id);

    List<Monument> filterMonuments(String searchQueryCity, String searchQueryName, String category);

    Monument edit(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, Long monumentId);
}

