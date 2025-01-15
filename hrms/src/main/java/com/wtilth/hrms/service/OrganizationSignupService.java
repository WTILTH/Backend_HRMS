package com.wtilth.hrms.service;

import com.wtilth.hrms.request.OrganizationSignupRequest;
import com.wtilth.hrms.entity.Organization;
import com.wtilth.hrms.entity.User;
import com.wtilth.hrms.repository.OrganizationRepository;
import com.wtilth.hrms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationSignupService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signupOrganization(OrganizationSignupRequest request) {
        // Step 1: Create organization
        Organization organization = new Organization();
        organization.setName(request.getOrganizationName());
        organization.setDescription(request.getOrganizationDescription());

        // Save the organization
        organizationRepository.save(organization);

        // Step 2: Create the user (admin user for the organization)
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // Encrypt password
        user.setRole(request.getRole());
        user.setOrganization(organization);

        // Save the user
        userRepository.save(user);
    }
}
