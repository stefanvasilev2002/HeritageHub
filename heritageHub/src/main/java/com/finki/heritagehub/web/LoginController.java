package com.finki.heritagehub.web;
import com.finki.heritagehub.model.exceptions.InvalidAppUserUsernameException;
import com.finki.heritagehub.model.exceptions.InvalidArgumentsException;
import com.finki.heritagehub.model.exceptions.InvalidUserCredentialsException;
import com.finki.heritagehub.model.AppUser;
import com.finki.heritagehub.service.AppUserService;
import com.finki.heritagehub.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/login")
public class LoginController {
    private final AppUserService appUserService;
    private final LanguageService languageService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(AppUserService appUserService, LanguageService languageService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.languageService = languageService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String loginPage(HttpServletRequest request, Model model){
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        languageService.changeLogin(model, request);
        return "login";
    }
    /*@PostMapping
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        AppUser user = null;

        try {
            user = appUserService.login(username, password);
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/login";
        }

        return "redirect:/";
    }*/
//@PostMapping
//public String login(@RequestParam String username,
//                    @RequestParam String password,
//                    Model model) {
//    AppUser user;
//
//    try {
//        user = appUserService.findByUsername(username);
//        // Use password encoder to check if entered password matches stored password
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new InvalidUserCredentialsException();
//        }
//    } catch (InvalidUserCredentialsException | InvalidArgumentsException | InvalidAppUserUsernameException exception) {
//        model.addAttribute("hasError", true);
//        model.addAttribute("error", exception.getMessage());
//        return "redirect:/login";
//    }
//
//    return "redirect:/";
//}

}
