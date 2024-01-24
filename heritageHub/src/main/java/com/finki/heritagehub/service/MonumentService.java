package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;

import java.util.List;

public interface MonumentService {
    List<Monument> getAllMonumentsByCategory(String category);

<<<<<<< Updated upstream:Domasna 3/heritageHub/src/main/java/com/finki/heritagehub/service/MonumentService.java
    @Autowired
    public MonumentService(MonumentRepository monumentRepository, CSVLoaderService csvLoaderService) {
        this.monumentRepository = monumentRepository;
        this.csvLoaderService = csvLoaderService;
        //loadMonuments();
    }
    public List<Monument> getAllMonumentsByCategory(String category) {
        if (Objects.equals(category, "historical")){
            return monumentRepository.findAllByHistoricIsTrueOrderById();
        }
        return monumentRepository.findAllByCulturalIsTrueOrderById();
    }

    public Monument getMonumentById(Long id) {
        return monumentRepository.findById(id).orElse(null);
    }
    public void loadMonuments() {
        // Load from CSV and save to the database
        List<Monument> monuments = csvLoaderService.loadMonumentsFromCsv();
        monumentRepository.saveAll(monuments);
    }

    public List<Monument> getAllMonuments() {
        return  monumentRepository.findAll();
    }
    public List<Monument> getAllOrderedMonuments(){
        return monumentRepository.findAllByOrderById();
    }
    public Monument addRatingById(Long id, double rating){
        Monument monument = monumentRepository.findMonumentById(id);
        double newRating = (monument.getRating() * monument.getNumRatings() + rating) / (monument.getNumRatings()+1);
        monument.setRating(newRating);
        monument.setNumRatings(monument.getNumRatings()+1);
        //monumentRepository.deleteMonumentById(id);
        monumentRepository.save(monument);
        return monument;
    }
    public Monument save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings, Long id){
        Monument monument;
        if(id == null){
            if(numRatings == 0){
                monument = new Monument(latitude,longitude,name,historic,cultural,city,0,0);
            }else{
                monument = new Monument(latitude,longitude,name,historic,cultural,city, rating, numRatings);
            }
        }else{
            if(numRatings == 0){
                monument = new Monument(id,latitude,longitude,name,historic,cultural,city,0,0);
            }else{
                monument = new Monument(id,latitude,longitude,name,historic,cultural,city, rating, numRatings);
            }
        }
        monumentRepository.save(monument);
        return monument;
    }
    public void deleteMonumentById(Long id){
        monumentRepository.deleteMonumentById(id);
    }
}
=======
    Monument getMonumentById(Long id);

    void loadMonuments();

    List<Monument> getAllMonuments();

    List<Monument> getAllOrderedMonuments();

    Monument addRatingById(Long id, double rating);

    Monument save(double latitude, double longitude, String name, boolean historic, boolean cultural, String city, double rating, int numRatings, Long id);

    void deleteMonument(Long id);
}
>>>>>>> Stashed changes:heritageHub/src/main/java/com/finki/heritagehub/service/MonumentService.java
