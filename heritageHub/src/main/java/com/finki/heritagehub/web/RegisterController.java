package com.finki.heritagehub.web;

import com.finki.heritagehub.model.RoleUser;
import com.finki.heritagehub.model.exceptions.InvalidAppUserEmailException;
import com.finki.heritagehub.model.exceptions.InvalidAppUserUsernameException;
import com.finki.heritagehub.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AppUserService appUserService;
    public RegisterController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping
    public String showRegister(Model model){
        return "register";
    }
    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model){
        try{
            appUserService.create(username,email,password,RoleUser.ROLE_USER);
        }catch (InvalidAppUserEmailException | InvalidAppUserUsernameException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "register";
        }

        return "redirect:/";
    }
}
