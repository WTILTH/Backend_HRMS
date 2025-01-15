package com.wtilth.hrms.service;

import com.wtilth.hrms.dto.LoginDTO;
import com.wtilth.hrms.dto.SignupDTO;
import com.wtilth.hrms.entity.Role;
import com.wtilth.hrms.entity.User;
import com.wtilth.hrms.repository.RoleRepository;
import com.wtilth.hrms.repository.UserRepository;
import com.wtilth.hrms.utils.JwtResponse;
import com.wtilth.hrms.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> signup(SignupDTO signupDTO) {
        Role role = roleRepository.findByRoleName(signupDTO.getRole());
        if (role == null) {
            return ResponseEntity.badRequest().body("Invalid role");
        }

        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        user.setRole(role);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<?> login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null && new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword())) {
            String token = JwtTokenUtil.generateToken(user.getUsername(), user.getRole().getName());
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

