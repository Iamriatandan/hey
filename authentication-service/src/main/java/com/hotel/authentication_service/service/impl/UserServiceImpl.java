package com.hotel.authentication_service.service.impl;

import com.hotel.authentication_service.entity.User;
import com.hotel.authentication_service.repository.UserRepository;
import com.hotel.authentication_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    // New method implementation
    @Override
    public User getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
