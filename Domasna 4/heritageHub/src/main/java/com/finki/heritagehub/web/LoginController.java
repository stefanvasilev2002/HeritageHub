package com.finki.heritagehub.web;
import com.finki.heritagehub.service.LanguageSelectionStrategy;
import com.finki.heritagehub.service.LanguageStrategyFactory;
import com.finki.heritagehub.service.impl.BackCommandImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/login")
public class LoginController {
    private final LanguageStrategyFactory languageStrategyFactory;
    private final BackCommandImpl backCommand;

    public LoginController(LanguageStrategyFactory languageService, BackCommandImpl backCommand) {
        this.languageStrategyFactory = languageService;
        this.backCommand = backCommand;
    }

    @GetMapping
    public String loginPage(HttpServletRequest request, Model model){
        backCommand.updateNavigationHistory(request);
        request.getSession().setAttribute("pathInfo", request.getRequestURI());
        LanguageSelectionStrategy strategy = languageStrategyFactory.getStrategy(request);
        strategy.changeLogin(model, request);
        return "login";
    }

}
