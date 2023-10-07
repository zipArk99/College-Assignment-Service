package com.example.classassignment.service;

import com.example.classassignment.model.User;

import java.util.List;

public interface UserServiceInterface {
    public User createUser(User user);

    public List<User> getAllUsers();
}
