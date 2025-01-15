package com.wtilth.hrms.repository;

import com.wtilth.hrms.entity.Role;
import com.wtilth.hrms.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    Optional<Salary> findByRoleAndOrganizationId(String role, Long organizationId);
}
