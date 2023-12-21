package com.finki.heritagehub.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/{id}")
    public String showLoginPage(@PathVariable Long id,
                                Model model,
                                HttpServletRequest request){
        model.addAttribute("id", id);
        if(request.getSession().getAttribute("isLogged") != null && (Boolean) request.getSession().getAttribute("isLogged")){
            return "redirect:/edit/" + id;
        }
        return "login";
    }
    @PostMapping
    public String login(@RequestParam(required = false) String username,
                        @RequestParam(required = false) String password,
                        @RequestParam(required = false) Long id,
                        Model model,
                        HttpServletRequest request) {

        // Placeholder response for demonstration purposes
        if ("admin".equals(username) && "admin".equals(password)) {
            request.getSession().setAttribute("isLogged", true);
            return "redirect:/edit/" + id;
        } else {
            request.getSession().setAttribute("isLogged", false);
            model.addAttribute("hasError", true);
            model.addAttribute("error", "Username or Password Incorrect");
            model.addAttribute("id", id);
            return "login";
        }
    }
}
