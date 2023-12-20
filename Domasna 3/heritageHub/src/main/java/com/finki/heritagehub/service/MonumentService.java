package com.finki.heritagehub.service;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.repository.MonumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class MonumentService {
    private final MonumentRepository monumentRepository;
    private final CSVLoaderService csvLoaderService;

    @Autowired
    public MonumentService(MonumentRepository monumentRepository, CSVLoaderService csvLoaderService) {
        this.monumentRepository = monumentRepository;
        this.csvLoaderService = csvLoaderService;
        loadMonuments();
    }
    public List<Monument> getAllMonumentsByCategory(String category) {
        if (Objects.equals(category, "historical")){
            return monumentRepository.findAllByHistoricIsTrue();
        }
        return monumentRepository.findAllByCulturalIsTrue();
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
    @Transactional
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