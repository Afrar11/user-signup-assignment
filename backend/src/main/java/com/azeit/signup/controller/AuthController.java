package com.azeit.signup.controller;

import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.azeit.signup.dto.RegisterRequest;
import com.azeit.signup.dto.RegisterResponse;
import com.azeit.signup.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest req) {
        RegisterResponse res = userService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
    String userOrEmail = loginData.get("usernameOrEmail");
    String password = loginData.get("password");
    boolean ok = userService.login(userOrEmail, password);
    if (ok) return ResponseEntity.ok(Map.of("message", "Login successful"));
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
    }

}
