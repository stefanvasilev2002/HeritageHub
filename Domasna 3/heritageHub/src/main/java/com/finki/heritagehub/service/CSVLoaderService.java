package com.finki.heritagehub.service;

import com.finki.heritagehub.model.Monument;

import java.util.List;

public interface CSVLoaderService {
    List<Monument> loadMonumentsFromCsv();
}
