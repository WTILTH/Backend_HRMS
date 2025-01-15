package com.wtilth.hrms.controller;

import com.wtilth.hrms.request.OrganizationSignupRequest;
import com.wtilth.hrms.service.OrganizationSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OrganizationSignupController {

    @Autowired
    private OrganizationSignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupOrganization(@RequestBody OrganizationSignupRequest request) {
        try {
            signupService.signupOrganization(request);
            return ResponseEntity.ok("Organization and user signup successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during signup: " + e.getMessage());
        }
    }
}
