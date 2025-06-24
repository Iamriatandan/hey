package com.hotel.authentication_service.service;

import com.hotel.authentication_service.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);

    // New method
    User getUserByUsername(String username);
}
