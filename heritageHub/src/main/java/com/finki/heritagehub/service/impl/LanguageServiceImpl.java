package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LanguageServiceImpl implements LanguageService {


    @Override
    public void changeMonuments(Model model, HttpServletRequest request) {
        String lang = checkSession(request);
    }

    private String checkSession(HttpServletRequest request) {
        if(request.getSession().getAttribute("lang") == null){
            request.getSession().setAttribute("lang", "en");
        }
        return (String) request.getSession().getAttribute("lang");
    }

    @Override
    public void changeEditMonument(Model model, HttpServletRequest request) {

    }

    @Override
    public void changeAddMonument(Model model, HttpServletRequest request) {

    }

    @Override
    public void changeAboutUs(Model model, HttpServletRequest request) {

    }

    @Override
    public void changeLogin(Model model, HttpServletRequest request) {

    }
    @Override
    public void changeCategories(Model model, HttpServletRequest request){

    }
    @Override
    public void changeMonumentDetails(Model model, HttpServletRequest request){

    }
    @Override
    public void changeLanguage(Model model, HttpServletRequest request, String pathInfo){
        if(pathInfo.startsWith("/edit")){
            this.changeEditMonument(model, request);
        }
        if(pathInfo.startsWith("/add")){
            this.changeAddMonument(model, request);
        }
        if(pathInfo.startsWith("/about-us")){
            this.changeAboutUs(model,request);
        }
        if(pathInfo.startsWith("/category")){
            this.changeMonuments(model,request);
        }
        if(pathInfo.equals("/")){
            this.changeCategories(model,request);
        }
        if(pathInfo.startsWith("/monument")){
            this.changeMonumentDetails(model, request);
        }
    }
}


