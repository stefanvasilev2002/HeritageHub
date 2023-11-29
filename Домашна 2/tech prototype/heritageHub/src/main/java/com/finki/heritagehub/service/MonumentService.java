package com.finki.heritagehub.service;
import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.repository.MonumentRepository;
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
}