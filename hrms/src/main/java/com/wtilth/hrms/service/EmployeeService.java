package com.wtilth.hrms.service;

import com.wtilth.hrms.entity.Employee;
import com.wtilth.hrms.entity.Organization;
import com.wtilth.hrms.entity.Salary;
import com.wtilth.hrms.repository.EmployeeRepository;
import com.wtilth.hrms.repository.OrganizationRepository;
import com.wtilth.hrms.repository.SalaryRepository;
import com.wtilth.hrms.request.EmployeeCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Transactional
    public Employee createEmployee(EmployeeCreateRequest request) {
        // Fetch the organization
        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Find the salary based on the role
        Salary salary = salaryRepository.findByRoleAndOrganizationId(request.getRole(), request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Salary structure not found for role"));

        // Create the employee
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(salary.getBaseSalary());
        employee.setOrganization(organization);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(Long organizationId) {
        return employeeRepository.findByOrganizationId(organizationId);
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Transactional
    public Employee updateEmployee(Long employeeId, EmployeeCreateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Salary salary = salaryRepository.findByRoleAndOrganizationId(request.getRole(), request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Salary structure not found for role"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(salary.getBaseSalary());
        employee.setOrganization(organization);

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
