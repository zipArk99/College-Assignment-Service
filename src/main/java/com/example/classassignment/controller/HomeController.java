package com.example.classassignment.controller;

import com.example.classassignment.model.User;
import com.example.classassignment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    //@PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/hello")
    public String home(){
        return "Accessed";
    }


}
