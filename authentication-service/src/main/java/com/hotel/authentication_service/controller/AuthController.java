package com.hotel.authentication_service.controller;

import com.hotel.authentication_service.entity.User;
import com.hotel.authentication_service.service.impl.UserServiceImpl;
import com.hotel.authentication_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        log.info("Registering user: {}", user.getUsername());
        userService.saveUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        log.info("Attempting login for user: {}", user.getUsername());
        User savedUser = userService.getUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), savedUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(savedUser.getUsername(), savedUser.getRole());
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
