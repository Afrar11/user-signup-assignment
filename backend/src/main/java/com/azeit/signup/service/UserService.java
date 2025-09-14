package com.azeit.signup.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azeit.signup.dto.RegisterRequest;
import com.azeit.signup.dto.RegisterResponse;
import com.azeit.signup.model.User;
import com.azeit.signup.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            throw new DataIntegrityViolationException("Username already exists");
        }

        User u = new User();
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setEmail(req.getEmail());
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setPhone(req.getPhone());
        u.setCountry(req.getCountry());
        u.setRoles(req.getRoles());

        User saved = userRepo.save(u);
        return new RegisterResponse(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getUsername(),
                saved.getPhone(),
                saved.getCountry(),
                saved.getRoles()
        );
    }

    public boolean login(String usernameOrEmail, String password) {
        return userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .filter(user -> encoder.matches(password, user.getPassword()))
                .isPresent();
    }
}
