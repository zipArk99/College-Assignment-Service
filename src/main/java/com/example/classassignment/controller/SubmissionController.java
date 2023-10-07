package com.example.classassignment.controller;

import com.example.classassignment.model.Submission;
import com.example.classassignment.model.SubmissionResponse;
import com.example.classassignment.service.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/user/student")
public class SubmissionController {

    @GetMapping("/test")
    public String work(){
        return "Working Submission";
    }
    @Autowired
   private SubmissionServiceInterface submissionService;
    @PostMapping("/submit")
    public SubmissionResponse sumbit(@RequestBody Submission request){
        return submissionService.submitAssignment(request);
    }
}
