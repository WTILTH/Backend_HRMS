package com.wtilth.hrms.controller;

import com.wtilth.hrms.entity.Employee;
import com.wtilth.hrms.repository.SalaryRepository;
import com.wtilth.hrms.request.EmployeeCreateRequest;
import com.wtilth.hrms.response.EmployeeResponse;
import com.wtilth.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryRepository salaryRepository;

    // Create Employee
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeCreateRequest request) {
        employeeService.createEmployee(request);
        return ResponseEntity.ok("Employee created successfully");
    }

    // Get Employee by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long employeeId) {
        Optional<Employee> employee = employeeService.getEmployee(employeeId);
        if (employee.isPresent()) {
            EmployeeResponse response = new EmployeeResponse();
            response.setId(employee.get().getId());
            response.setFirstName(employee.get().getFirstName());
            response.setLastName(employee.get().getLastName());
            response.setEmail(employee.get().getEmail());
            response.setPosition(employee.get().getPosition());
            response.setSalary(employee.get().getSalary());
            response.setOrganizationName(employee.get().getOrganization().getName());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(null);
    }

    // Get all employees by organization
    @GetMapping
    public List<EmployeeResponse> getAllEmployees(@RequestParam Long organizationId) {
        List<Employee> employees = employeeService.getAllEmployees(organizationId);
        return employees.stream().map(employee -> {
            EmployeeResponse response = new EmployeeResponse();
            response.setId(employee.getId());
            response.setFirstName(employee.getFirstName());
            response.setLastName(employee.getLastName());
            response.setEmail(employee.getEmail());
            response.setPosition(employee.getPosition());
            response.setSalary(employee.getSalary());
            response.setOrganizationName(employee.getOrganization().getName());
            return response;
        }).toList();
    }

    // Update Employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeCreateRequest request) {
        employeeService.updateEmployee(employeeId, request);
        return ResponseEntity.ok("Employee updated successfully");
    }

    // Delete Employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}