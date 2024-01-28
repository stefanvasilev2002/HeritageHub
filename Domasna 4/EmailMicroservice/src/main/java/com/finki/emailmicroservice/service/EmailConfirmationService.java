package com.finki.emailmicroservice.service;

public interface EmailConfirmationService {
    void sendConfirmationEmail(String toEmail, String confirmationLink);
}
