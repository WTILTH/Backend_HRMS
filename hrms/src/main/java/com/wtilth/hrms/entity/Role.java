package com.wtilth.hrms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and Setters
}
