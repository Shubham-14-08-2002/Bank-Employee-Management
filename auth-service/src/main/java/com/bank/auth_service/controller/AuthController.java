package com.bank.auth_service.controller;

import com.bank.auth_service.service.JwtUtil;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Manager-only login
        if ("admin".equals(username) && "admin123".equals(password)) {
            String token = jwtUtil.generateToken(username, "ROLE_MANAGER");
            return Map.of("token", token);
        } else {
            throw new RuntimeException("Invalid username or password credentials!");
        }
    }
}