package com.finki.emailmicrservice.service.impl;

import com.finki.emailmicrservice.service.EmailConfirmationService;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    @Override
    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        
    }
}
