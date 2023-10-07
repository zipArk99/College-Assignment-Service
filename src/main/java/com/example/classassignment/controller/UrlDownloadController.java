package com.example.classassignment.controller;

import com.example.classassignment.model.Submission;
import com.example.classassignment.service.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/downloadURL")
public class UrlDownloadController {

    @Autowired
    private SubmissionServiceInterface submissionService;
    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        Submission submission = submissionService.getFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(submission.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename=\"" + submission.getFileName() + "\"").body(new ByteArrayResource(submission.getSubmittedFile()));

    }
}
