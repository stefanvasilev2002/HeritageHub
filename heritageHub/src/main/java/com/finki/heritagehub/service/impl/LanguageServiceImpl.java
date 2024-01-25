package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Service
public class LanguageServiceImpl implements LanguageService {
    private static String language;

    public void changeLanguage(){
        if (Objects.equals(language, "en")){
            language = "mk";
        }
        else {
            language = "en";
        }
    }
    @Override
    public void changeMonuments(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){

        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }
    }

    private void setDefaultLanguage(HttpServletRequest request) {
        if(request.getSession().getAttribute("lang") == null){
            request.getSession().setAttribute("lang", "en");
        }
    }

    @Override
    public void changeEditMonument(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){

        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }

    }

    @Override
    public void changeAddMonument(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){

        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }
    }

    @Override
    public void changeAboutUs(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){


        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }
    }
    @Override
    public void changeLogin(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){

        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }
    }
    @Override
    public void changeCategories(Model model, HttpServletRequest request){
        setDefaultLanguage(request);
        if ("en".equals(request.getSession().getAttribute("lang"))) {

        } else if ("mk".equals((request.getSession().getAttribute("lang")))) {

        }
    }
    @Override
    public void changeMonumentDetails(Model model, HttpServletRequest request){
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){

        }else if(request.getSession().getAttribute("lang").equals("mk")){

        }
    }

    @Override
    public void changeRegister(Model model, HttpServletRequest request) {

    }
//    @Override
//    public void changeLanguage(Model model, HttpServletRequest request){
//        setDefaultLanguage(request);
//        String pathInfo = request.getPathInfo();
//        if(pathInfo.startsWith("/edit")){
//            this.changeEditMonument(model, request);
//        }
//        if(pathInfo.startsWith("/add")){
//            this.changeAddMonument(model, request);
//        }
//        if(pathInfo.startsWith("/about-us")){
//            this.changeAboutUs(model,request);
//        }
//        if(pathInfo.startsWith("/category")){
//            this.changeMonuments(model,request);
//        }
//        if(pathInfo.equals("/")){
//            this.changeCategories(model,request);
//        }
//        if(pathInfo.startsWith("/monument")){
//            this.changeMonumentDetails(model, request);
//        }
//    }
}


