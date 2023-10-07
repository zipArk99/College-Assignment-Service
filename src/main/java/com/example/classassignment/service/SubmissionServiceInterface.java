package com.example.classassignment.service;

import com.example.classassignment.model.Submission;
import com.example.classassignment.model.SubmissionResponse;

public interface SubmissionServiceInterface {
    public SubmissionResponse submitAssignment(Submission submission);
}
