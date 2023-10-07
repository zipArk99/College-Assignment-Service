package com.example.classassignment.controller;

import com.example.classassignment.model.Submission;
import com.example.classassignment.model.SubmissionResponse;
import com.example.classassignment.service.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/user/student")
public class SubmissionController {

    @GetMapping("/test")
    public String work() {
        return "Working Submission";
    }

    @Autowired
    private SubmissionServiceInterface submissionService;

    @PostMapping("/submit")
    public SubmissionResponse submit(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "assignmentId") Integer assignmentId, @RequestParam(name = "studentId") Integer studentId) {
        return submissionService.submitAssignment(file, assignmentId, studentId);
    }



}
