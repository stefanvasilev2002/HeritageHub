package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageSelectionStrategy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EnglishLanguageStrategy implements LanguageSelectionStrategy {
    private void setNavEnglish(Model model){
        model.addAttribute("navHeader", "HeritageHub");
        model.addAttribute("navHome", "Home");
        model.addAttribute("navHistorical", "Historical");
        model.addAttribute("navCultural", "Cultural");
        model.addAttribute("navAboutUs", "About us");
        model.addAttribute("navLogIn", "Log In");
        model.addAttribute("navLogOut", "Log Out");
    }
    @Override
    public void changeMonuments(Model model, HttpServletRequest request) {
        model.addAttribute("titleHistorical", "Historical Monuments");
        model.addAttribute("titleCultural", "Cultural Monuments");
        setNavEnglish(model);
        model.addAttribute("bodyHeaderHistorical", "Historical Monuments");
        model.addAttribute("bodyHeaderCultural", "Cultural Monuments");
        model.addAttribute("bodySearchByName", "Search by name");
        model.addAttribute("bodySearchByCity", "Search by city");
        model.addAttribute("bodySearchButton", "Search");
        model.addAttribute("bodyLatitude", "Latitude: ");
        model.addAttribute("bodyLongitude", "Longitude: ");
        model.addAttribute("bodyCity", "City: ");
        model.addAttribute("bodyRating", "Rating: ");
        model.addAttribute("bodyView", "View");
        model.addAttribute("bodyDelete", "Delete");
    }

    @Override
    public void changeEditMonument(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Edit Monument");
        setNavEnglish(model);
        model.addAttribute("bodyHeader", "Edit Monument");
        model.addAttribute("bodyLatitude", "Latitude:");
        model.addAttribute("bodyLongitude", "Longitude:");
        model.addAttribute("bodyName", "Name:");
        model.addAttribute("bodyHistoric", "Historic");
        model.addAttribute("bodyCultural", "Cultural");
        model.addAttribute("bodyCity", "City:");
        model.addAttribute("bodyRating", "Rating (0-5):");
        model.addAttribute("bodyNumRatings", "Number of Ratings:");
        model.addAttribute("backButton", "Back");
    }

    @Override
    public void changeAddMonument(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Create Monument");
        setNavEnglish(model);
        model.addAttribute("bodyHeader", "Create Monument");
        model.addAttribute("bodyLatitude", "Latitude:");
        model.addAttribute("bodyLongitude", "Longitude:");
        model.addAttribute("bodyName", "Name:");
        model.addAttribute("bodyHistoric", "Historic");
        model.addAttribute("bodyCultural", "Cultural");
        model.addAttribute("bodyCity", "City:");
        model.addAttribute("bodyRating", "Rating (0-5):");
        model.addAttribute("bodyNumRatings", "Number of Ratings:");
        model.addAttribute("backButton", "Back");
    }

    @Override
    public void changeAboutUs(Model model, HttpServletRequest request) {
        model.addAttribute("title", "About us");
        setNavEnglish(model);
        model.addAttribute("bodyHeader", "About Us");
        model.addAttribute("bodyTeamMembers", "Team Members:");
        model.addAttribute("bodyTeamMember1", "Stefan Vasilev - 211021");
        model.addAttribute("bodyTeamMember2", "Andrej Genchovski - 211229");
        model.addAttribute("bodyTeamMember3", "Izabela Eftimova - 211038");
        model.addAttribute("bodyTeamMember4", "Aleksandar Sandev - 211213");
        model.addAttribute("bodyTeamMember5", "Isidora Boshkova - 211203");
        model.addAttribute("bodyMenotrs", "Mentors:");
        model.addAttribute("bodyMenotr1", "Prof. Dr. Ljupcho Antovski");
        model.addAttribute("bodyMenotr2", "Prof. Dr. Petre Lameski");
        model.addAttribute("bodyContact", "Contact");
    }

    @Override
    public void changeLogin(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Admin Login");
        setNavEnglish(model);
        model.addAttribute("bodyUsername", "Username:");
        model.addAttribute("bodyPassword", "Password:");
        model.addAttribute("bodyLogIn", "Log in");
        model.addAttribute("bodyRegister", "Register");
        model.addAttribute("backButton", "Back");
    }

    @Override
    public void changeCategories(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Heritage Hub Home Page");
        setNavEnglish(model);
        model.addAttribute("bodyHeader", "Explore Macedonia's Heritage Sites");
        model.addAttribute("bodyCulturalMonuments", "Cultural Monuments");
        model.addAttribute("bodyHistoricalMonuments", "Historical Monuments");
        model.addAttribute("bodyAddMonument", "Add Monument");
    }

    @Override
    public void changeMonumentDetails(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Map Display");
        setNavEnglish(model);
        model.addAttribute("bodyYourRating", "Your rating: ");
        model.addAttribute("bodyRateThisMonument", "Rate this monument");
        model.addAttribute("bodyRating", "Rating (0-5): ");
        model.addAttribute("bodySubmitButton", "Submit");
        model.addAttribute("backButton", "Back");
    }

    @Override
    public void changeRegister(Model model, HttpServletRequest request) {
        changeLogin(model, request);
        model.addAttribute("bodyEmail", "E-mail:");
    }
}
