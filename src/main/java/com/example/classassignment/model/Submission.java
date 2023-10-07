package com.example.classassignment.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    @Column(name = "score" ,nullable = true )
    private Integer score;

    @Column(name = "submitted_at")
    private LocalDate submittedAt;

    @Column(name = "approved", columnDefinition ="boolean default false")
    private boolean approved;



    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Assignment.class)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    private User student;

    public Submission(Integer submissionId, Integer score, LocalDate submittedAt, boolean approved, Assignment assignment, User student) {
        this.submissionId = submissionId;
        this.score = score;
        this.submittedAt = submittedAt;
        this.approved = approved;
        this.assignment = assignment;
        this.student = student;
    }
    public Submission(){

    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
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

    @Override
    public String toString() {
        return "Submission{" +
                "submissionId=" + submissionId +
                ", score=" + score +
                ", submittedAt=" + submittedAt +
                ", approved=" + approved +
                ", assignment=" + assignment +
                ", student=" + student +
                '}';
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

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
