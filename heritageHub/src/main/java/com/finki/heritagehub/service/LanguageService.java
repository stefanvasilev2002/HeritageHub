package com.finki.heritagehub.service;

import jakarta.servlet.http.HttpServletRequest;
import org.h2.engine.Session;
import org.springframework.ui.Model;

public interface LanguageService {
    void changeMonuments(Model model, HttpServletRequest request);
    void changeEditMonument(Model model, HttpServletRequest request);
    void changeAddMonument(Model model, HttpServletRequest request);
    void changeAboutUs(Model model, HttpServletRequest request);
    void changeLogin(Model model, HttpServletRequest request);
    void changeCategories(Model model, HttpServletRequest request);
    void changeMonumentDetails(Model model, HttpServletRequest request);
    void changeLanguage(Model model, HttpServletRequest request, String pathInfo);

}
