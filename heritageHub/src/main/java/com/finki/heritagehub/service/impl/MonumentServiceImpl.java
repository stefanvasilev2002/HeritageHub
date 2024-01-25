package com.finki.heritagehub.service.impl;
import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.model.Rating;
import com.finki.heritagehub.repository.MonumentRepository;
import com.finki.heritagehub.service.AppUserService;
import com.finki.heritagehub.service.MonumentFactory;
import com.finki.heritagehub.service.MonumentService;
import com.finki.heritagehub.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    @Autowired
    public MonumentServiceImpl(MonumentRepository monumentRepository, CSVLoaderServiceImpl csvLoaderServiceImpl, MonumentFactory monumentFactory, RatingService ratingService, AppUserService appUserService) {
        this.monumentRepository = monumentRepository;
        this.csvLoaderServiceImpl = csvLoaderServiceImpl;
        this.monumentFactory = monumentFactory;
        this.ratingService = ratingService;
        this.appUserService = appUserService;
        //monumentRepository.deleteAll();
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
    @Override
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
    public Monument addRatingById(Long id, double rating) {
        AppUser user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=null;
        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username of the authenticated user
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
        return monumentRepository.save(monument);
    }
    @Override
    public Monument edit(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings, Long id){
        Monument monument;

        if(numRatings == 0){
            monument = new Monument(id,latitude,longitude,name,historic,cultural,city,0,0);
        }else{
            monument = new Monument(id,latitude,longitude,name,historic,cultural,city, rating, numRatings);
        }
        monumentRepository.save(monument);
        return monument;
    }
    @Override
    public Monument save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city) {
        Monument monument = monumentFactory.createMonument(latitude, longitude, name, historic, cultural, city);
        monumentRepository.save(monument);
        return monument;
    }
    @Override
    public void deleteMonument(Long id) {
        Monument monument = getMonumentById(id);
        monumentRepository.delete(monument);
    }

    @Override
    public List<Monument> filterMonuments(String searchQueryCity, String searchQueryName) {
        List<Monument> monuments;
        if (searchQueryCity == null && searchQueryName != null){
            monuments = getAllMonuments().stream()
                    .filter(x-> x.getName().toLowerCase().contains(searchQueryName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if (searchQueryName == null && searchQueryCity != null){
            monuments = getAllMonuments().stream()
                    .filter(x-> x.getCity().toLowerCase().contains(searchQueryCity.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else {
            monuments = getAllMonuments().stream()
                    .filter(x-> x.getName()
                            .toLowerCase()
                            .contains(searchQueryName
                                    .toLowerCase()) &&
                            x.getCity()
                                    .toLowerCase()
                                    .contains(searchQueryCity.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return monuments;
    }
}