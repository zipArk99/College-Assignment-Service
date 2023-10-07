package com.example.classassignment.service;

import com.example.classassignment.exception.AssignmentException;
import com.example.classassignment.exception.UserException;
import com.example.classassignment.model.Assignment;
import com.example.classassignment.model.Submission;
import com.example.classassignment.model.SubmissionResponse;
import com.example.classassignment.model.User;
import com.example.classassignment.repository.AssignmentRepository;
import com.example.classassignment.repository.SubmissionRepository;
import com.example.classassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl implements SubmissionServiceInterface{
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Override

    public SubmissionResponse submitAssignment(Submission submission) {
        User student = userRepository.findById(submission.getStudent().getId()).orElseThrow(()->new UserException("User not Found"));
        Assignment assignment = assignmentRepository.findById(submission.getAssignment().getAssignmentId()).orElseThrow(()->new AssignmentException("Assignment Not Found"));
        submission.setStudent(student);
        submission.setAssignment(assignment);
        Submission newsubmission= submissionRepository.save(submission);
        return new SubmissionResponse(newsubmission.getSubmissionId(),newsubmission.getScore(),newsubmission.getSubmittedAt(),newsubmission.isApproved());


    }
}
