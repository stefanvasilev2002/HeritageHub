package com.finki.heritagehub.web;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.MonumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MonumentController {
    private final MonumentService monumentService;

    public MonumentController(MonumentService monumentService) {
        this.monumentService = monumentService;
    }

    @GetMapping("/")
    public String showCategories(Model model) {
        model.addAttribute("monumentList", monumentService.getAllMonuments());
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
    public String searchByName(@RequestParam String searchQuery, @RequestParam String category, Model model) {
        List<Monument> monuments = monumentService.getAllMonumentsByCategory(category);
        monuments = monuments.stream()
                .filter(x-> x.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        model.addAttribute("monuments", monuments);
        model.addAttribute("category", category);
        return "monuments";
    }
    @GetMapping("/monument/{id}")
    public String showMonumentDetails(@PathVariable Long id, Model model) {
        Monument monument = monumentService.getMonumentById(id);
        model.addAttribute("monument", monument);
        return "monumentDetails";
    }
    @GetMapping("/about-us")
    public String showAboutUs(){
        return "about-us";
    }
}