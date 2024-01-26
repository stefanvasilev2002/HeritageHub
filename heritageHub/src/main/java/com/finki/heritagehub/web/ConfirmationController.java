package com.finki.heritagehub.web;

import com.finki.heritagehub.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationController {

    private final AppUserService appUserService;

    public ConfirmationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        // Validate the token and register the user
        if (appUserService.confirmRegistration(token)) {
            return ResponseEntity.ok("User registration confirmed successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }
    }
}