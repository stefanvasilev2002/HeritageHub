package com.finki.heritagehub.web;

import com.finki.heritagehub.model.ConfirmationRequest;
import com.finki.heritagehub.model.ConfirmationTokenGenerator;
import com.finki.heritagehub.model.RoleUser;
import com.finki.heritagehub.service.AppUserService;
import com.finki.heritagehub.service.LanguageSelectionStrategy;
import com.finki.heritagehub.service.LanguageStrategyFactory;
import com.finki.heritagehub.service.impl.BackCommandImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AppUserService appUserService;
    private final LanguageStrategyFactory languageStrategyFactory;
    private final BackCommandImpl backCommand;
    private final RestTemplate restTemplate;

    public RegisterController(AppUserService appUserService, LanguageStrategyFactory languageStrategyFactory, BackCommandImpl backCommand, RestTemplate restTemplate) {
        this.appUserService = appUserService;
        this.languageStrategyFactory = languageStrategyFactory;
        this.backCommand = backCommand;
        this.restTemplate = restTemplate;
    }


    @GetMapping
    public String showRegister(Model model,
                               HttpServletRequest request) {
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeRegister(model, request);
        return "register";
    }
    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model,
                               HttpServletRequest request){

        String token = ConfirmationTokenGenerator.generateToken();

        try{
            appUserService.create(username,email,password,RoleUser.ROLE_USER, token);
            ConfirmationRequest confirmationRequest = new ConfirmationRequest(email,
                    ConfirmationTokenGenerator.BASE_URL + token);

            restTemplate
                    .postForEntity("https://mail-microservice-116ef2400221.herokuapp.com/confirmation/send-confirmation",
                            confirmationRequest, String.class);


        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Username or email already exists.");
            request.getSession().setAttribute("pathInfo", request.getRequestURI());
            LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
            strategy.changeRegister(model, request);
            return "register";
        }

        return "redirect:/";
    }
}
