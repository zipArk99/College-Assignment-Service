package com.example.classassignment.controller;


import com.example.classassignment.exception.AuthenticationException;
import com.example.classassignment.model.JwtRequest;
import com.example.classassignment.model.JwtResponse;
import com.example.classassignment.model.User;
import com.example.classassignment.service.JwtServiceImpl;
import com.example.classassignment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtServiceImpl jwtService;
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserServiceImpl userService;

    //  @PostMapping("/register")
    // public ResponseEntity<JwtResponse> registerUser(@RequestBody JwtRequest request) {

    // }

    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody User user) {
        User newuser = userService.createUser(user);
        return ResponseEntity.ok(newuser);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtRequest request) {
        this.authenticate(request.getEmail(), request.getPassword());
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String jwtToken = jwtService.generateToken(new HashMap<>(), user);
        JwtResponse jwtResponse = new JwtResponse(user.getUsername(), jwtToken);
        return ResponseEntity.ok(jwtResponse);
    }


    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authManager.authenticate(auth);

        } catch (BadCredentialsException exception) {
            throw new AuthenticationException("Wrong password or username");
        }
    }
}
