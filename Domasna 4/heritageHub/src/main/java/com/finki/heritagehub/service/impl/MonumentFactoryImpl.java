package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.MonumentFactory;
import org.springframework.stereotype.Service;

@Service
public class MonumentFactoryImpl implements MonumentFactory {
    public Monument createMonument(double latitude,
                                          double longitude,
                                          String name,
                                          boolean historic,
                                          boolean cultural,
                                          String city) {
        return new Monument(latitude, longitude, name, historic, cultural, city);
    }
}
