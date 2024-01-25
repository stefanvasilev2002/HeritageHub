package com.finki.heritagehub.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface LanguageSelectionStrategy {
    void changeMonuments(Model model, HttpServletRequest request);
    void changeEditMonument(Model model, HttpServletRequest request);
    void changeAddMonument(Model model, HttpServletRequest request);
    void changeAboutUs(Model model, HttpServletRequest request);
    void changeLogin(Model model, HttpServletRequest request);
    void changeCategories(Model model, HttpServletRequest request);
    void changeMonumentDetails(Model model, HttpServletRequest request);
    void changeRegister(Model model, HttpServletRequest request);
}

