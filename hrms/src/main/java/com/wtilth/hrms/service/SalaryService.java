package com.wtilth.hrms.service;

import com.wtilth.hrms.entity.Salary;
import com.wtilth.hrms.repository.OrganizationRepository;
import com.wtilth.hrms.repository.SalaryRepository;
import com.wtilth.hrms.request.SalaryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public void addSalary(SalaryRequest salaryRequest) {
        // Validate organization exists
        organizationRepository.findById(salaryRequest.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Create a new salary entry
        Salary salary = new Salary();
        salary.setRole(salaryRequest.getRole());
        salary.setBaseSalary(salaryRequest.getBaseSalary());
        salary.setOrganization(organizationRepository.findById(salaryRequest.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found")));

        salaryRepository.save(salary);
    }

    @Transactional
    public void updateSalary(Long salaryId, SalaryRequest salaryRequest) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary not found"));

        // Update salary details
        salary.setRole(salaryRequest.getRole());
        salary.setBaseSalary(salaryRequest.getBaseSalary());
        salary.setOrganization(organizationRepository.findById(salaryRequest.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found")));

        salaryRepository.save(salary);
    }

    @Transactional
    public void deleteSalary(Long salaryId) {
        Salary salary = salaryRepository.findById(salaryId)
                .orElseThrow(() -> new RuntimeException("Salary not found"));

        salaryRepository.delete(salary);
    }
}
