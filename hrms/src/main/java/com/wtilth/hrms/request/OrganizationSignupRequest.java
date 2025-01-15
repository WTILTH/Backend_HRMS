package com.wtilth.hrms.request;

import com.wtilth.hrms.entity.Role;
import lombok.Data;

@Data
public class OrganizationSignupRequest {

    private String organizationName;
    private String organizationDescription;
    private String username;
    private String password;
    private Role role;  // e.g., user, admin, etc.

}

