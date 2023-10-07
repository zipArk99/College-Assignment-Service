package com.example.classassignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class AssignmentUpdateRequest {


    private Integer assignmentId;

    private String title;

    private String description;

    private LocalDate dueDate;

    public AssignmentUpdateRequest(Integer assignmentId, String title, String description, LocalDate dueDate) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
