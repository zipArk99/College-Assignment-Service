package com.example.classassignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class SubmissionResponse {

    private Integer submissionId;

    private Integer score;

    private LocalDate submittedAt;

    private boolean approved;

    public SubmissionResponse(Integer submissionId, Integer score, LocalDate submittedAt, boolean approved) {
        this.submissionId = submissionId;
        this.score = score;
        this.submittedAt = submittedAt;
        this.approved = approved;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDate submittedAt) {
        this.submittedAt = submittedAt;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
