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
import org.springframework.core.io.Resource;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
public class SubmissionServiceImpl implements SubmissionServiceInterface{
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Override
    public SubmissionResponse submitAssignment(MultipartFile file,Integer assignmentId,Integer studentId) {
        Submission submission = new Submission();
        User student = userRepository.findById(studentId).orElseThrow(()->new UserException("User not Found for Submission"));
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(()->new AssignmentException("Assignment Not Found"));

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            submission.setSubmittedFile(file.getBytes());
            submission.setFileName(fileName);
            submission.setFileType(file.getContentType());

        }catch (Exception exception){
            throw  new RuntimeException("Runtime Exception Occured");
        }
        submission.setStudent(student);
        submission.setAssignment(assignment);
        Submission newsubmission= submissionRepository.save(submission);
        String downloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadURL/").path(newsubmission.getSubmissionId().toString()).toUriString();
        return new SubmissionResponse(newsubmission.getSubmissionId(),newsubmission.getScore(),newsubmission.getSubmittedAt(),newsubmission.isApproved(),downloadUrl,newsubmission.getFileName(),newsubmission.getFileType());


    }

    @Override
    public Submission getFile(String fileId) {
        return  submissionRepository.findById(Long.valueOf(fileId)).orElseThrow(()->new RuntimeException("Exception"));
    }


}
