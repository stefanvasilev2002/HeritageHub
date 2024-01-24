package com.finki.heritagehub.web;
import com.finki.heritagehub.model.exceptions.InvalidArgumentsException;
import com.finki.heritagehub.model.exceptions.InvalidUserCredentialsException;
import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public String loginPage(){

        return "login";
    }




    /*@PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        // Placeholder response for demonstration purposes
        AppUser user = null;

        try {
            user = appUserService.login(username, password);
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/login";
        }

        return "redirect:/"
    }*/
}
