package com.finki.heritagehub.service.impl;
import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.model.Rating;
import com.finki.heritagehub.repository.MonumentRepository;
import com.finki.heritagehub.service.AppUserService;
import com.finki.heritagehub.service.MonumentFactory;
import com.finki.heritagehub.service.MonumentService;
import com.finki.heritagehub.service.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MonumentServiceImpl implements MonumentService {
    private final MonumentRepository monumentRepository;
    private final CSVLoaderServiceImpl csvLoaderServiceImpl;
    private final MonumentFactory monumentFactory;
    private final RatingService ratingService;
    private final AppUserService appUserService;
    public MonumentServiceImpl(MonumentRepository monumentRepository, CSVLoaderServiceImpl csvLoaderServiceImpl, MonumentFactory monumentFactory, RatingService ratingService, AppUserService appUserService) {
        this.monumentRepository = monumentRepository;
        this.csvLoaderServiceImpl = csvLoaderServiceImpl;
        this.monumentFactory = monumentFactory;
        this.ratingService = ratingService;
        this.appUserService = appUserService;

        //loadMonuments();
    }
    @Override
    public List<Monument> getAllMonumentsByCategory(String category) {
        if (Objects.equals(category, "historical")){
            return monumentRepository.findAllByHistoricIsTrueOrderById();
        }
        return monumentRepository.findAllByCulturalIsTrueOrderById();
    }
    @Override
    public Monument getMonumentById(Long id) {
        return monumentRepository.findById(id).orElse(null);
    }

    //    @PostConstruct
    public void loadMonuments() {
        // Load from CSV and save to the database
        List<Monument> monuments = csvLoaderServiceImpl.loadMonumentsFromCsv();
        monumentRepository.saveAll(monuments);
    }
    @Override
    public List<Monument> getAllMonuments() {
        return  monumentRepository.findAll();
    }
    @Override
    public List<Monument> getAllOrderedMonuments(){
        return monumentRepository.findAllByOrderById();
    }
    @Override
    public void addRatingById(Long id, double rating) {
        AppUser user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=null;

        if (authentication != null && authentication.isAuthenticated()) {
            username = authentication.getName();
        }
        user = appUserService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        Monument monument = monumentRepository.findMonumentById(id);
        if (ratingService.getRatingByUserAndMonument(user, monument).isEmpty()){
            monument.setRating(monument.getRating() + rating);
            monument.setNumRatings(monument.getNumRatings() + 1);
            ratingService.save(new Rating(user, monument, rating));
        }
        else {
            monument.setRating(monument.getRating() - ratingService.ratingByUserForMonument(user, monument) + rating);
            Rating rating1 = ratingService.getRatingByUserAndMonument(user, monument).get();
            rating1.setValue(rating);
            ratingService.save(rating1);
        }
        monumentRepository.save(monument);
    }
    @Override
    public Monument edit(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, Long id){
        Monument monument = monumentRepository.findMonumentById(id);

        monument.setLatitude(latitude);
        monument.setLongitude(longitude);
        monument.setName(name);
        monument.setHistoric(historic);
        monument.setCultural(cultural);
        monument.setCity(city);

        return monumentRepository.save(monument);
    }
    @Override
    public void save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city) {
        Monument monument = monumentFactory.createMonument(latitude, longitude, name, historic, cultural, city);
        monumentRepository.save(monument);
    }
    @Override
    public void deleteMonument(Long id) {
        monumentRepository.deleteById(id);
    }

    @Override
    public List<Monument> filterMonuments(String searchQueryCity, String searchQueryName, String category) {
        List<Monument> monuments;
        if (searchQueryCity != null && searchQueryName != null){
            monuments =  monumentRepository.findAllByCityContainingAndNameContaining(searchQueryCity, searchQueryName);
        }
        else if (searchQueryCity != null){
            monuments = monumentRepository.findAllByCityContaining(searchQueryCity);
        }
        else if (searchQueryName != null){
            monuments = monumentRepository.findAllByNameContaining(searchQueryName);
        }
        else monuments = monumentRepository.findAll();

        if (category.equals("historical")){
             return monuments.stream().filter(Monument::isHistoric).collect(Collectors.toList());
        }
        return monuments.stream().filter(Monument::isCultural).collect(Collectors.toList());
    }
}