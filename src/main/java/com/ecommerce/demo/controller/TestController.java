package com.ecommerce.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.ecommerce.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/email")
    public String testEmail() {
        emailService.sendOrderConfirmation("test@example.com", "Test User", "TEST123", 999, "[{\"name\":\"Test\"}]");
        return "Email sent! Check your terminal!";
    }
}
