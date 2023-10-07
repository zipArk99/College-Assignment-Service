package com.example.classassignment.service;

import com.example.classassignment.model.Submission;
import com.example.classassignment.model.SubmissionResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SubmissionServiceInterface {

    Submission getFile(String fileId);

    public SubmissionResponse submitAssignment(MultipartFile file, Integer assignmentId, Integer studentId);
}
