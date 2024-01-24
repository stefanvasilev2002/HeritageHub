package com.finki.heritagehub.service.impl;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.repository.MonumentRepository;
import com.finki.heritagehub.service.MonumentService;
import com.finki.heritagehub.service.impl.CSVLoaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MonumentServiceImpl implements MonumentService {
    private final MonumentRepository monumentRepository;
    private final CSVLoaderServiceImpl csvLoaderServiceImpl;

    @Autowired
    public MonumentServiceImpl(MonumentRepository monumentRepository, CSVLoaderServiceImpl csvLoaderServiceImpl) {
        this.monumentRepository = monumentRepository;
        this.csvLoaderServiceImpl = csvLoaderServiceImpl;
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
    public Monument addRatingById(Long id, double rating){
        Monument monument = monumentRepository.findMonumentById(id);
        double newRating = (monument.getRating() * monument.getNumRatings() + rating) / (monument.getNumRatings()+1);
        monument.setRating(newRating);
        monument.setNumRatings(monument.getNumRatings()+1);
        //monumentRepository.deleteMonumentById(id);
        monumentRepository.save(monument);
        return monument;
    }
    @Override
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
    @Override
    public void deleteMonument(Long id) {
        Monument monument = getMonumentById(id);
        monumentRepository.delete(monument);
    }
}