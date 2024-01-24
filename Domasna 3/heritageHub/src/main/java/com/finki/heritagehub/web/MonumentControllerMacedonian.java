package com.finki.heritagehub.web;

import com.finki.heritagehub.model.Monument;
import com.finki.heritagehub.service.impl.MonumentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mk")
public class MonumentControllerMacedonian {
    private final MonumentServiceImpl monumentServiceImpl;

    public MonumentControllerMacedonian(MonumentServiceImpl monumentServiceImpl) {
        this.monumentServiceImpl = monumentServiceImpl;
    }

    @GetMapping("")
    public String showCategories(Model model) {
<<<<<<< Updated upstream:Domasna 3/heritageHub/src/main/java/com/finki/heritagehub/web/MonumentControllerMacedonian.java
        model.addAttribute("monumentList", monumentService.getAllOrderedMonuments());
=======
        model.addAttribute("monumentList", monumentServiceImpl.getAllOrderedMonuments());
        model.addAttribute("numHistoricalMonuments", monumentServiceImpl.getAllMonumentsByCategory("historical").size());
        model.addAttribute("numCulturalMonuments", monumentServiceImpl.getAllMonumentsByCategory("cultural").size());
>>>>>>> Stashed changes:heritageHub/src/main/java/com/finki/heritagehub/web/MonumentControllerMacedonian.java
        return "categories_macedonian";
    }

    @GetMapping("/category/{category}")
    public String showMonumentsByCategory(@PathVariable String category, Model model) {
        List<Monument> monuments = monumentServiceImpl.getAllMonumentsByCategory(category);
        model.addAttribute("monuments", monuments);
        model.addAttribute("category",category);
        return "monuments_macedonian";
    }
    @GetMapping("/category/search")
    public String search(@RequestParam(required = false) String searchQueryName,
                         @RequestParam(required = false) String searchQueryCity,
                         @RequestParam String category,
                         Model model) {
        List<Monument> monuments = monumentServiceImpl.getAllMonumentsByCategory(category);
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
                    .filter(x-> x.getName().toLowerCase().contains(searchQueryName.toLowerCase()) && x.getCity().toLowerCase().contains(searchQueryCity.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("monuments", monuments);
        model.addAttribute("category", category);
        return "monuments_macedonian";
    }
    @GetMapping("/monument/{id}")
    public String showMonumentDetails(@PathVariable Long id,
                                      Model model,
                                      HttpServletRequest request) {
        Boolean rated = (Boolean) request.getSession().getAttribute(String.format("isRated%d",id));
        Double rating = (Double) request.getSession().getAttribute(String.format("rating%d",id));
        model.addAttribute("rated", rated);
        model.addAttribute("rating", rating);
        Monument monument = monumentServiceImpl.getMonumentById(id);
        model.addAttribute("monument", monument);
        return "monumentDetails_macedonian";
    }
    @GetMapping("/about-us")
    public String showAboutUs(){
        return "about-us_macedonian";
    }

    @GetMapping("/add")
    public String showAddForm(){
        return "addMonument_macedonian";
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
        monumentServiceImpl.save(latitude,longitude,name,historic,cultural,city,rating,numRatings, null);
        return "redirect:/mk";
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
            monumentServiceImpl.addRatingById(monumentId, rating);
        }
        return "redirect:/mk/monument/" + monumentId;

    }
    @GetMapping("/edit/{id}")
    public String editMonument(@PathVariable Long id,
                               Model model,
                               HttpServletRequest request){
        if(request.getSession().getAttribute("isLogged") == null || !(Boolean) request.getSession().getAttribute("isLogged")){
            return "redirect:/mk/login/" + id;
        }
        Monument monument = monumentServiceImpl.getMonumentById(id);
        if(monument == null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", String.format("Monument with id %d not found", id));
            return "editMonument_macedonian";
        }
        model.addAttribute("monument", monument);
        return "editMonument_macedonian";
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

        Monument monument = monumentServiceImpl.save(latitude, longitude, name, historic, cultural, city, rating, numRatings, monumentId);

        return "redirect:/mk/monument/" + monument.getId();
    }
<<<<<<< Updated upstream:Domasna 3/heritageHub/src/main/java/com/finki/heritagehub/web/MonumentControllerMacedonian.java
=======
    @PostMapping("/deleteMonument")
    public String deleteMonument(@RequestParam Long monumentId,
                                 HttpServletRequest request){
        if(request.getSession().getAttribute("isLogged") == null || !(Boolean) request.getSession().getAttribute("isLogged")){
            return "redirect:/mk/login/" + monumentId;
        }
        monumentServiceImpl.deleteMonument(monumentId);
        return "redirect:/mk";
    }
>>>>>>> Stashed changes:heritageHub/src/main/java/com/finki/heritagehub/web/MonumentControllerMacedonian.java

}
