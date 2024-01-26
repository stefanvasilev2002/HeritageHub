package com.finki.heritagehub.service.impl;

import com.finki.heritagehub.service.Command;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Getter
@Service
public class BackCommandImpl implements Command {
    private final Stack<String> navigationHistory = new Stack<>();

    @Override
    public void execute(HttpServletRequest request) {
        if (!navigationHistory.isEmpty()) {
            navigationHistory.pop();
            String previousPage = navigationHistory.pop();

            request.setAttribute("backUrl", previousPage);
        }
        else request.setAttribute("backUrl", null);

    }

    @Override
    public void updateNavigationHistory(HttpServletRequest request) {
        if (!navigationHistory.isEmpty() && navigationHistory.peek().equals(request.getRequestURI())){

        }
        else navigationHistory.push(request.getRequestURI());
    }

}
