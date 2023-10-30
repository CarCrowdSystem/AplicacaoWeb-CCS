package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.response.EmailValidationResponse;
import carcrowdsystem.ccs.services.EmailValidationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {
    private final EmailValidationService emailValidationService;

    public EmailValidationController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @GetMapping("/validate-email")
    public EmailValidationResponse validateEmail(@RequestParam String email) {
        return emailValidationService.validateEmail(email);
    }
}
