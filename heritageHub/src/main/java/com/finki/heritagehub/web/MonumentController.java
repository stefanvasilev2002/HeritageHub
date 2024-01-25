package com.finki.heritagehub.web;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.LanguageService;
import com.finki.heritagehub.service.MonumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MonumentController {
    private final MonumentService monumentService;
    private final LanguageService languageService;

    public MonumentController(MonumentService monumentService, LanguageService languageService) {
        this.monumentService = monumentService;
        this.languageService = languageService;
    }

    @GetMapping("/")
    public String showCategories(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeCategories(model, request);

        model.addAttribute("monumentList", monumentService.getAllOrderedMonuments());
        model.addAttribute("numHistoricalMonuments", monumentService
                                                                .getAllMonumentsByCategory("historical").size());
        model.addAttribute("numCulturalMonuments", monumentService
                                                                .getAllMonumentsByCategory("cultural").size());

        return "categories";
    }

    @GetMapping("/category/{category}")
    public String showMonumentsByCategory(@PathVariable String category,
                                          Model model,
                                          HttpServletRequest request) {
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeMonuments(model, request);

        model.addAttribute("monuments", monumentService
                                                    .getAllMonumentsByCategory(category));
        model.addAttribute("category",category);

        return "monuments";
    }
    @GetMapping("/category/search")
    public String search(@RequestParam(required = false) String searchQueryName,
                         @RequestParam(required = false) String searchQueryCity,
                         @RequestParam String category,
                         Model model,
                         HttpServletRequest request) {
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeMonuments(model, request);

        List<Monument> monuments = monumentService.filterMonuments(searchQueryCity, searchQueryName);
        model.addAttribute("monuments", monuments);
        model.addAttribute("category", category);
        return "monuments";
    }
    @GetMapping("/monument/{id}")
    public String showMonumentDetails(@PathVariable Long id,
                                      Model model,
                                      HttpServletRequest request) {
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeMonumentDetails(model, request);
        //ToDO
        Boolean rated = (Boolean) request.getSession().getAttribute(String.format("isRated%d",id));
        Double rating = (Double) request.getSession().getAttribute(String.format("rating%d",id));
        model.addAttribute("rated", rated);
        model.addAttribute("rating", rating);
        Monument monument = monumentService.getMonumentById(id);
        model.addAttribute("monument", monument);
        return "monumentDetails";
    }
    @GetMapping("/about-us")
    public String showAboutUs(Model model,
                              HttpServletRequest request){
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeAboutUs(model, request);
        return "about-us";
    }

    @GetMapping("/add")
    public String showAddForm(Model model,
                              HttpServletRequest request){
        languageService.changeAddMonument(model, request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());

        return "addMonument";
    }
    @PostMapping("/add")
    public String submitAddForm(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String name,
            @RequestParam(required = false) boolean historic,
            @RequestParam(required = false) boolean cultural,
            @RequestParam String city,
            @RequestParam(defaultValue = "0") double rating,
            @RequestParam(defaultValue = "0") int numRatings
    ) {
        monumentService.save(latitude,longitude,name,historic,cultural,city,rating,numRatings);
        return "redirect:/";
    }
    @PostMapping("/addRating")
    public String addRating(
            @RequestParam("monumentId") Long monumentId,
            @RequestParam double rating,
            HttpServletRequest request
    ) {
        if(rating >= 0 && rating <= 5){
            request.getSession().setAttribute(String.format("isRated%d", monumentId), true);
            request.getSession().setAttribute(String.format("rating%d",monumentId), rating);
            monumentService.addRatingById(monumentId, rating);
        }

        return "redirect:/monument/" + monumentId;

    }
    @GetMapping("/edit/{id}")
    public String editMonument(@PathVariable Long id,
                               Model model,
                               HttpServletRequest request){
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeEditMonument(model, request);

        Monument monument = monumentService.getMonumentById(id);
        if(monument == null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", String.format("Monument with id %d not found", id));
            return "editMonument";
        }
        model.addAttribute("monument", monument);

        return "editMonument";
    }
    @PostMapping("/editMonument")
    public String editMonument(@RequestParam("monumentId") Long monumentId,
                               @RequestParam double latitude,
                               @RequestParam double longitude,
                               @RequestParam String name,
                               @RequestParam(required = false) boolean historic,
                               @RequestParam(required = false) boolean cultural,
                               @RequestParam String city,
                               @RequestParam double rating,
                               @RequestParam int numRatings) {

        Monument monument = monumentService.save(latitude, longitude, name, historic, cultural, city, rating, numRatings, monumentId);

        return "redirect:/monument/" + monument.getId();
    }
    @PostMapping("/deleteMonument/{id}")
    public String deleteMonument(@PathVariable Long id,
                                 HttpServletRequest request){
        //todo
        if(request.getSession().getAttribute("isLogged") == null || !(Boolean) request.getSession().getAttribute("isLogged")){
            return "redirect:/login/" + id;
        }
        monumentService.deleteMonument(id);
        return "redirect:/";
    }
    @GetMapping("/mk")
    String changeLanguageMacedonian(HttpServletRequest request){
        request.getSession().setAttribute("lang", "mk");
        String pathInfo = (String) request.getSession().getAttribute("pathInfo");

        return "redirect:" + pathInfo;
    }
    @GetMapping("/en")
    String changeLanguageEnglish(HttpServletRequest request){

        request.getSession().setAttribute("lang", "en");
        String pathInfo = (String) request.getSession().getAttribute("pathInfo");

        return "redirect:" + pathInfo;
    }
}