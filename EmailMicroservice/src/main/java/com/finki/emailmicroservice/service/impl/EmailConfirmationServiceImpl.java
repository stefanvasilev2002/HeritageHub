package com.finki.emailmicroservice.service.impl;

import com.finki.emailmicroservice.service.EmailConfirmationService;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;

import java.io.IOException;

@Service
public class EmailConfirmationServiceImpl implements EmailConfirmationService {
    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;
    @Override
    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        Email from = new Email("heritagehubmk@gmail.com");
        Email to = new Email(toEmail);
        String subject = "Confirm Your Registration";
        String confirm = "<a href=" + confirmationLink + ">Confirm Your Account</a>";
        Content content = new Content("text/html", "<p>Hello,</p>" +
                "<p>Thank you for registering on our site, HeritageHubMK.</p>" +
                "<p>Please click the following link to confirm your registration: </p>" + confirm
                + "<p>If you didn't request this, please ignore this email. Your account will not be confirmed.</p>");

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
