package com.user.service.UserService.services;

import com.user.service.UserService.entities.User;

import java.util.List;

public interface UserService{

    // Create user
    User saveUser(User user);

    // Get all users
    List<User> getAllUser();

    // Get single user by ID
    User getUser(String userId);

    // Delete user
    void deleteUser(String userId);

    // Update user
    User updateUser(User user, String userId);
}