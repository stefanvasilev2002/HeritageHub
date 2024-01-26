package com.finki.emailmicrservice.service;

public interface EmailConfirmationService {
    void sendConfirmationEmail(String toEmail, String confirmationLink);
}
