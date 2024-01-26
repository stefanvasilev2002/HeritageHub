package com.finki.emailmicroservice.web;

import com.finki.emailmicroservice.model.ConfirmationRequest;
import com.finki.emailmicroservice.service.EmailConfirmationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationController {
    private final EmailConfirmationService emailConfirmationService;

    public ConfirmationController(EmailConfirmationService emailConfirmationService) {
        this.emailConfirmationService = emailConfirmationService;
    }

    @PostMapping("/send-confirmation")
    public ResponseEntity<String> sendConfirmation(@RequestBody ConfirmationRequest confirmationRequest) {

        emailConfirmationService.sendConfirmationEmail(confirmationRequest.getEmail(), confirmationRequest.getConfirmationLink());

        return ResponseEntity.ok("Confirmation email sent successfully.");
    }
}
