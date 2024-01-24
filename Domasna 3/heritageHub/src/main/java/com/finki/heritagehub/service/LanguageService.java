package com.finki.heritagehub.service;

import org.springframework.ui.Model;

public interface LanguageService {
    void changeMonuments(Model model);
    void changeEditMonument(Model model);
    void changeAddMonument(Model model);
    void changeAboutUs(Model model);
    void changeLogin(Model model);

}
