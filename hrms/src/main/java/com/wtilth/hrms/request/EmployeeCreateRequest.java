package com.wtilth.hrms.request;

import lombok.Data;

@Data
public class EmployeeCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Long organizationId;
    private String role;

}
