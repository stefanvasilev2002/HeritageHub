package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CSVLoaderService {
    private final String CSV_FILE;

    public CSVLoaderService() {
        CSV_FILE = "src/main/resources/final_data.csv";;
    }

    public List<Monument> loadMonumentsFromCsv() {
        List<Monument> monuments = new ArrayList<>();

        try{

            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
            String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length < 4){
                        continue;
                    }
                    Monument monument = new Monument();
                    monument.setId(Long.parseLong(data[0]));
                    monument.setLatitude(Double.parseDouble(data[1]));
                    monument.setLongitude(Double.parseDouble(data[2]));
                    monument.setName(data[3]);
                    if (data.length == 6) {
                        monument.setHistoric(!Objects.equals(data[4], ""));
                        monument.setCultural(!Objects.equals(data[5], ""));
                    }
                    else {
                        if (Objects.equals(data[4], "attraction") || Objects.equals(data[4], "artwork")){
                            monument.setHistoric(false);
                            monument.setCultural(true);
                        }
                        else {
                            monument.setHistoric(true);
                            monument.setCultural(false);
                        }
                    }
                    monuments.add(monument);
                }
        } catch (Exception e) {
            e.printStackTrace(); // handle the exception appropriately
        }

        return monuments;
    }
}
