package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LanguageServiceImpl implements LanguageService {


    @Override
    public void changeMonuments(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){
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
        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("titleHistorical", "Историски Соменици");
            model.addAttribute("titleCultural", "Културни Споменици");
            setNavMacedonian(model);
            model.addAttribute("bodyHeaderHistorical", "Историски Соменици");
            model.addAttribute("bodyHeaderCultural", "Културни Споменици");
            model.addAttribute("bodySearchByName", "Пребарај по име");
            model.addAttribute("bodySearchByCity", "Пребарај по град");
            model.addAttribute("bodySearchButton", "Пребарај");
            model.addAttribute("bodyLatitude", "Географска ширина: ");
            model.addAttribute("bodyLongitude", "Географска должина: ");
            model.addAttribute("bodyCity", "Град: ");
            model.addAttribute("bodyRating", "Оцена: ");
            model.addAttribute("bodyView", "Погледни");
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
        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("title", "Промени Споменик");
            setNavMacedonian(model);
            model.addAttribute("bodyHeader", "Измени споменик");
            model.addAttribute("bodyLatitude", "Географска ширина:");
            model.addAttribute("bodyLongitude", "Географска должина:");
            model.addAttribute("bodyName", "Име:");
            model.addAttribute("bodyHistoric", "Историско");
            model.addAttribute("bodyCultural", "Културно");
            model.addAttribute("bodyCity", "Град:");
            model.addAttribute("bodyRating", "Оцена (0-5):");
            model.addAttribute("bodyNumRatings", "Број на оцени:");
            model.addAttribute("backButton", "Назад");
        }

    }

    @Override
    public void changeAddMonument(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){
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
        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("title", "Додади споменик");
            setNavMacedonian(model);
            model.addAttribute("bodyHeader", "Направи споменик");
            model.addAttribute("bodyLatitude", "Географска ширина:");
            model.addAttribute("bodyLongitude", "Географска должина:");
            model.addAttribute("bodyName", "Име:");
            model.addAttribute("bodyHistoric", "Историско");
            model.addAttribute("bodyCultural", "Културно");
            model.addAttribute("bodyCity", "Град:");
            model.addAttribute("bodyRating", "Оцена (0-5):");
            model.addAttribute("bodyNumRatings", "Број на оцени:");
            model.addAttribute("backButton", "Назад");
        }
    }

    @Override
    public void changeAboutUs(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){
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



        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("title", "За нас");
            setNavMacedonian(model);
            model.addAttribute("bodyHeader", "За нас");
            model.addAttribute("bodyTeamMembers", "Членови на тимот:");
            model.addAttribute("bodyTeamMember1", "Стефан Василев - 211021");
            model.addAttribute("bodyTeamMember2", "Андреј Генчовски - 211229");
            model.addAttribute("bodyTeamMember3", "Изабела Ефтимова - 211038");
            model.addAttribute("bodyTeamMember4", "Александар Сандев - 211213");
            model.addAttribute("bodyTeamMember5", "Исидора Бошкова - 211203");
            model.addAttribute("bodyMenotrs", "Ментори:");
            model.addAttribute("bodyMenotr1", "проф. д-р Љупчо Антовски");
            model.addAttribute("bodyMenotr2", "проф. д-р Петре Ламески");
            model.addAttribute("bodyContact", "Контакт");



        }
    }
    private void setNavEnglish(Model model){
        model.addAttribute("navHeader", "HeritageHub");
        model.addAttribute("navHome", "Home");
        model.addAttribute("navHistorical", "Historical");
        model.addAttribute("navCultural", "Cultural");
        model.addAttribute("navAboutUs", "About us");
    }
    private void setNavMacedonian(Model model){
        model.addAttribute("navHeader", "HeritageHub");
        model.addAttribute("navHome", "Почетна");
        model.addAttribute("navHistorical", "Историски");
        model.addAttribute("navCultural", "Културни");
        model.addAttribute("navAboutUs", "За нас");
    }
    @Override
    public void changeLogin(Model model, HttpServletRequest request) {
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){
            model.addAttribute("title", "Admin Login");
            setNavEnglish(model);
            model.addAttribute("bodyUsername", "Username:");
            model.addAttribute("bodyPassword", "Password:");
            model.addAttribute("bodyLogIn", "Log in");
            model.addAttribute("backButton", "Back");
        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("title", "Админ Логирање");
            setNavMacedonian(model);
            model.addAttribute("bodyUsername", "Корисничко име:");
            model.addAttribute("bodyPassword", "Лозинка:");
            model.addAttribute("bodyLogIn", "Логирај се");
            model.addAttribute("backButton", "Назад");
        }
    }
    @Override
    public void changeCategories(Model model, HttpServletRequest request){
        setDefaultLanguage(request);
        if ("en".equals(request.getSession().getAttribute("lang"))) {
            model.addAttribute("title", "Heritage Hub Home Page");
            setNavEnglish(model);
            model.addAttribute("bodyHeader", "Explore Macedonia's Heritage Sites");
            model.addAttribute("bodyCulturalMonuments", "Cultural Monuments");
            model.addAttribute("bodyHistoricalMonuments", "Historical Monuments");
            model.addAttribute("bodyAddMonument", "Add Monument");
        } else if ("mk".equals((request.getSession().getAttribute("lang")))) {
            model.addAttribute("title", "Heritage Hub Почетна");
            setNavMacedonian(model);
            model.addAttribute("bodyHeader", "Запознај ги македонските културно историски локации");
            model.addAttribute("bodyCulturalMonuments", "Култирни Споменици");
            model.addAttribute("bodyHistoricalMonuments", "Историски Споменици");
            model.addAttribute("bodyAddMonument", "Додади споменик");
        }
    }
    @Override
    public void changeMonumentDetails(Model model, HttpServletRequest request){
        setDefaultLanguage(request);
        if(request.getSession().getAttribute("lang").equals("en")){
            model.addAttribute("title", "Map Display");
            setNavEnglish(model);
            model.addAttribute("bodyYourRating", "Your rating: ");
            model.addAttribute("bodyRateThisMonument", "Rate this monument");
            model.addAttribute("bodyRating", "Rating (0-5): ");
            model.addAttribute("bodySubmitButton", "Submit");
            model.addAttribute("backButton", "Back");
        }else if(request.getSession().getAttribute("lang").equals("mk")){
            model.addAttribute("title", "Детали");
            setNavMacedonian(model);
            model.addAttribute("bodyYourRating", "Твојата оцена: ");
            model.addAttribute("bodyRateThisMonument", "Оцени го овој споменик");
            model.addAttribute("bodyRating", "Оцена (0-5): ");
            model.addAttribute("bodySubmitButton", "Потврди");
            model.addAttribute("backButton", "Назад");
        }
    }
    @Override
    public void changeLanguage(Model model, HttpServletRequest request){
        setDefaultLanguage(request);
        String pathInfo = request.getPathInfo();
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


