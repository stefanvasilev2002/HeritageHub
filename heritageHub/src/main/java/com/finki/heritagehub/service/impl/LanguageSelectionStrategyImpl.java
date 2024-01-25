package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.LanguageSelectionStrategy;
import com.finki.heritagehub.service.LanguageStrategyFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class LanguageSelectionStrategyImpl implements LanguageStrategyFactory {
    @Override
    public LanguageSelectionStrategy getStrategy(HttpServletRequest request) {
        if(request.getSession().getAttribute("lang") == null){
            request.getSession().setAttribute("lang", "en");
        }

        if(request.getSession().getAttribute("lang").equals("en")){
            return new EnglishLanguageStrategy();
        }else {
            return new MacedonianLanguageStrategy();
        }
    }
}
