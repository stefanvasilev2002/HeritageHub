package com.finki.emailmicroservice.service.impl;

import com.finki.emailmicroservice.service.EmailConfirmationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    private final JavaMailSender javaMailSender;

    public EmailConfirmationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("heritagehubmk@outlook.com");

        String content = "Hello," +
                "Thank you for registering on our site, HeritageHubMK." +
                "Please click the following link to confirm your registration: " + confirmationLink +
                " . If you didn't request this, please ignore this email. Your account will not be confirmed.";
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);
    }
}
