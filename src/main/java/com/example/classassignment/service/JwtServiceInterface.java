package com.example.classassignment.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {
    public String getUsername(String jwtToken);
    public Boolean validateToken(String jwtToken, UserDetails userDetails);
}
