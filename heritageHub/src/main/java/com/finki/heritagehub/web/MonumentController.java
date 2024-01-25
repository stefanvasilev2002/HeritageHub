package com.finki.heritagehub.web;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.LanguageService;
import com.finki.heritagehub.service.MonumentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MonumentController {
    private final MonumentService monumentService;
    private final LanguageService languageService;

    public MonumentController(MonumentService monumentService, LanguageService languageService) {
        this.monumentService = monumentService;
        this.languageService = languageService;
    }

    @GetMapping("/")
    public String showCategories(Model model) {
        model.addAttribute("monumentList", monumentService.getAllOrderedMonuments());
        model.addAttribute("monumentList", monumentService.getAllOrderedMonuments());
        model.addAttribute("numHistoricalMonuments", monumentService.getAllMonumentsByCategory("historical").size());
        model.addAttribute("numCulturalMonuments", monumentService.getAllMonumentsByCategory("cultural").size());
        //laguageService.changeCategories(model);
        return "categories";
    }

    @GetMapping("/category/{category}")
    public String showMonumentsByCategory(@PathVariable String category, Model model) {
        List<Monument> monuments = monumentService.getAllMonumentsByCategory(category);
        model.addAttribute("monuments", monuments);
        model.addAttribute("category",category);
        return "monuments";
    }
    @GetMapping("/category/search")
    public String search(@RequestParam(required = false) String searchQueryName,
                         @RequestParam(required = false) String searchQueryCity,
                         @RequestParam String category,
                         Model model) {
        List<Monument> monuments = monumentService.getAllMonumentsByCategory(category);
        if (searchQueryCity == null && searchQueryName != null){
            monuments = monuments.stream()
                    .filter(x-> x.getName().toLowerCase().contains(searchQueryName.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else if (searchQueryName == null && searchQueryCity != null){
            monuments = monuments.stream()
                    .filter(x-> x.getCity().toLowerCase().contains(searchQueryCity.toLowerCase()))
                    .collect(Collectors.toList());
        }
        else {
            monuments = monuments.stream()
                    .filter(x-> x.getName()
                            .toLowerCase()
                            .contains(searchQueryName
                                    .toLowerCase()) &&
                            x.getCity()
                            .toLowerCase()
                                    .contains(searchQueryCity.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("monuments", monuments);
        model.addAttribute("category", category);
        return "monuments";
    }
    @GetMapping("/monument/{id}")
    public String showMonumentDetails(@PathVariable Long id,
                                      Model model,
                                      HttpServletRequest request) {
        Boolean rated = (Boolean) request.getSession().getAttribute(String.format("isRated%d",id));
        Double rating = (Double) request.getSession().getAttribute(String.format("rating%d",id));
        model.addAttribute("rated", rated);
        model.addAttribute("rating", rating);
        Monument monument = monumentService.getMonumentById(id);
        model.addAttribute("monument", monument);
        return "monumentDetails";
    }
    @GetMapping("/about-us")
    public String showAboutUs(){
        return "about-us";
    }

    @GetMapping("/add")
    public String showAddForm(){
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
        monumentService.save(latitude,longitude,name,historic,cultural,city,rating,numRatings, null);
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
            Monument monument = monumentService.addRatingById(monumentId, rating);
        }
        return "redirect:/monument/" + monumentId;

    }
    @GetMapping("/edit/{id}")
    public String editMonument(@PathVariable Long id,
                               Model model,
                               HttpServletRequest request){
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
    @PostMapping("/deleteMonument")
    public String deleteMonument(@RequestParam Long monumentId,
                                 HttpServletRequest request){
        if(request.getSession().getAttribute("isLogged") == null || !(Boolean) request.getSession().getAttribute("isLogged")){
            return "redirect:/login/" + monumentId;
        }
        monumentService.deleteMonument(monumentId);
        return "redirect:/";
    }
    @PostMapping("/mk")
    String changeLanguageMacedonian(HttpServletRequest request, Model model){
        request.setAttribute("lang", "mk");
        String pathInfo = request.getPathInfo();

        languageService.changeLanguage(model,request);

        return "redirect:/" + pathInfo;
    }
    @PostMapping("/en")
    String changeLanguageEnglish(HttpServletRequest request, Model model){
        request.setAttribute("lang", "en");
        String pathInfo = request.getPathInfo();

        languageService.changeLanguage(model,request);


        return "redirect:/" + pathInfo;
    }
}