package com.finki.heritagehub.web;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.Command;
import com.finki.heritagehub.service.LanguageSelectionStrategy;
import com.finki.heritagehub.service.LanguageStrategyFactory;
import com.finki.heritagehub.service.MonumentService;
import com.finki.heritagehub.service.impl.BackCommandImpl;
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
    private final LanguageStrategyFactory languageStrategyFactory;
    private final BackCommandImpl backCommand;

    public MonumentController(MonumentService monumentService, LanguageStrategyFactory languageService, BackCommandImpl backCommand) {
        this.monumentService = monumentService;
        this.languageStrategyFactory = languageService;
        this.backCommand = backCommand;
    }

    @GetMapping("/")
    public String showCategories(Model model, HttpServletRequest request) {
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeCategories(model, request);

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
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeMonuments(model, request);

        model.addAttribute("monuments", monumentService
                                                    .getAllMonumentsByCategory(category));
        model.addAttribute("category",category);

        return "monuments";
    }
    @GetMapping("/category/{category}/search")
    public String search(@RequestParam(required = false) String searchQueryName,
                         @RequestParam(required = false) String searchQueryCity,
                         @PathVariable String category,
                         Model model,
                         HttpServletRequest request) {
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeMonuments(model, request);

        List<Monument> monuments = monumentService.filterMonuments(searchQueryCity, searchQueryName, category);
        model.addAttribute("monuments", monuments);
        model.addAttribute("category", category);
        return "monuments";
    }
    @GetMapping("/monument/{id}")
    public String showMonumentDetails(@PathVariable Long id,
                                      Model model,
                                      HttpServletRequest request) {
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeMonumentDetails(model, request);
        Monument monument = monumentService.getMonumentById(id);
        model.addAttribute("monument", monument);
        return "monumentDetails";
    }
    @GetMapping("/about-us")
    public String showAboutUs(Model model,
                              HttpServletRequest request){
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeAboutUs(model, request);
        return "about-us";
    }

    @GetMapping("/add")
    public String showAddForm(Model model,
                              HttpServletRequest request){
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeAddMonument(model, request);

        return "addMonument";
    }
    @PostMapping("/add")
    public String submitAddForm(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String name,
            @RequestParam(required = false) boolean historic,
            @RequestParam(required = false) boolean cultural,
            @RequestParam String city
    ) {
        monumentService.save(latitude,longitude,name,historic,cultural,city);
        return "redirect:/";
    }
    @PostMapping("/addRating")
    public String addRating(
            @RequestParam("monumentId") Long monumentId,
            @RequestParam double rating) {
        monumentService.addRatingById(monumentId, rating);

        return "redirect:/monument/" + monumentId;

    }
    @GetMapping("/edit/{id}")
    public String editMonument(@PathVariable Long id,
                               Model model,
                               HttpServletRequest request){
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeEditMonument(model, request);

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
                               @RequestParam String city) {

        Monument monument = monumentService.edit(latitude, longitude, name, historic, cultural, city, monumentId);

        return "redirect:/monument/" + monument.getId();
    }
    @PostMapping("/deleteMonument/{id}")
    public String deleteMonument(@PathVariable Long id,
                                 HttpServletRequest request){

        monumentService.deleteMonument(id);
        return "redirect:" + request.getSession().getAttribute("pathInfo");
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
    @GetMapping("/back")
    public String goBack(HttpServletRequest request) {
        backCommand.execute(request);
        String backUrl = (String) request.getAttribute("backUrl");
        return "redirect:" + (backUrl != null ? backUrl : "/");
    }
}