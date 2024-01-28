package com.finki.heritagehub.service;

import jakarta.servlet.http.HttpServletRequest;

public interface LanguageStrategyFactory {
    LanguageSelectionStrategy getStrategy(HttpServletRequest request);
}
