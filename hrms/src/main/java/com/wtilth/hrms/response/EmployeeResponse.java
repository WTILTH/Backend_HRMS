package com.wtilth.hrms.response;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String organizationName;
    private Double salary;
}
