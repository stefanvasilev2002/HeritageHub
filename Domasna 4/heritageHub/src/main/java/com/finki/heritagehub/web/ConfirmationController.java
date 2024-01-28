package com.finki.heritagehub.web;

import com.finki.heritagehub.model.ConfirmationRequest;
import com.finki.heritagehub.model.ConfirmationTokenGenerator;
import com.finki.heritagehub.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationController {
    private final RestTemplate restTemplate;

    private final AppUserService appUserService;

    public ConfirmationController(RestTemplate restTemplate, AppUserService appUserService) {
        this.restTemplate = restTemplate;
        this.appUserService = appUserService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        if (appUserService.confirmRegistration(token)) {
            return ResponseEntity.ok("User registration confirmed successfully. Return to the app and re-login if you are currently logged in, to apply the changes.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
    @PostMapping("/resend")
    public ResponseEntity<String> resendConfirmation(@RequestParam String email) {
        String token = ConfirmationTokenGenerator.generateToken();

        appUserService.setNewToken(token, email);

        ConfirmationRequest confirmationRequest = new ConfirmationRequest(email,
                ConfirmationTokenGenerator.BASE_URL + token);

        //local testing
           /* restTemplate
                    .postForEntity("http://localhost:9090/confirmation/send-confirmation",
                            confirmationRequest, String.class);*/
        // prod testing
        restTemplate
                .postForEntity("https://mail-microservice-116ef2400221.herokuapp.com/confirmation/send-confirmation",
                        confirmationRequest, String.class);

        return ResponseEntity.ok("Confirmation link is resent to the provided email!");
    }
}