package com.wtilth.hrms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private Double baseSalary;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

}
