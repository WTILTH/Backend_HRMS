package com.wtilth.hrms.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


}
