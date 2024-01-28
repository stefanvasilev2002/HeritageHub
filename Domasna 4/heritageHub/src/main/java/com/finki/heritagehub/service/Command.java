package com.finki.heritagehub.service;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    void execute(HttpServletRequest request);
    void updateNavigationHistory(HttpServletRequest request);
}
