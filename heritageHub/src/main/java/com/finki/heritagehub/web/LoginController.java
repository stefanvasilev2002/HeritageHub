package com.finki.heritagehub.web;
import com.finki.heritagehub.service.LanguageSelectionStrategy;
import com.finki.heritagehub.service.LanguageStrategyFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/login")
public class LoginController {
    private final LanguageStrategyFactory languageStrategyFactory;

    public LoginController(LanguageStrategyFactory languageService) {
        this.languageStrategyFactory = languageService;
    }

    @GetMapping
    public String loginPage(HttpServletRequest request, Model model){
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeLogin(model, request);
        return "login";
    }

}
