package com.example.classassignment.service;

import com.example.classassignment.model.User;
import com.example.classassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserServiceInterface{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User createUser(User user) {
        String pass= passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        return userRepository.save(user);
    }
}
