package com.wtilth.hrms.controller;

import com.wtilth.hrms.request.SalaryRequest;
import com.wtilth.hrms.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salaries")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    // Add Salary for specific role and organization
    @PostMapping
    public ResponseEntity<String> addSalary(@RequestBody SalaryRequest salaryRequest) {
        salaryService.addSalary(salaryRequest);
        return ResponseEntity.ok("Salary added successfully for role: " + salaryRequest.getRole());
    }

    // Update Salary for specific role and organization
    @PutMapping("/{salaryId}")
    public ResponseEntity<String> updateSalary(@PathVariable Long salaryId, @RequestBody SalaryRequest salaryRequest) {
        salaryService.updateSalary(salaryId, salaryRequest);
        return ResponseEntity.ok("Salary updated successfully for role: " + salaryRequest.getRole());
    }

    // Delete Salary for specific role and organization
    @DeleteMapping("/{salaryId}")
    public ResponseEntity<String> deleteSalary(@PathVariable Long salaryId) {
        salaryService.deleteSalary(salaryId);
        return ResponseEntity.ok("Salary deleted successfully.");
    }
}
