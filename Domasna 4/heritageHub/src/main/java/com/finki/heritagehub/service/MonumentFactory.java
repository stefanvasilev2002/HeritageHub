package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;

public interface MonumentFactory {
    Monument createMonument(double latitude,
                                          double longitude,
                                          String name,
                                          boolean historic,
                                          boolean cultural,
                                          String city);
}
