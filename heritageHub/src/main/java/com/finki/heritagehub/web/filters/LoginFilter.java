package com.finki.heritagehub.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "auth-filter", urlPatterns = "/edit/*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Check if the user is logged in
        boolean isLoggedIn = isLoggedIn(request);

        // Extracting the ID from the URL if it's available
        String id = extractId(request);

        // Check if the requested URL is /edit/{id} and the user is not logged in
        if (isEditRequest(request) && !isLoggedIn) {
            if (id != null) {
                response.sendRedirect("/login/" + id); // Redirect to the login page with ID
            } else {
                response.sendRedirect("/login"); // If ID is not available, redirect to /login
            }
        } else {
            filterChain.doFilter(request, response); // Continue with the filter chain for other requests
        }
    }

    private boolean isEditRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.matches("^/edit/\\d+$"); // Check if the URI matches /edit/{id}
    }

    private String extractId(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String[] pathParts = requestURI.split("/");
        if (pathParts.length > 2) { // Assuming /edit/{id} structure
            return pathParts[2]; // Extracting the ID part
        }
        return null; // Return null if ID is not found
    }


    private boolean isLoggedIn(HttpServletRequest request) {
        // Check if the user is logged in based on your session variable
        // Modify this logic according to how you set the "isLogged" session variable
        Object isLogged = request.getSession().getAttribute("isLogged");
        return isLogged != null && (boolean) isLogged;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

