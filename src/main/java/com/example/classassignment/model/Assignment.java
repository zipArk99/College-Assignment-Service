package com.example.classassignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;




@Entity
@Table(name = "assignments")
public class Assignment {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDate dueDate;


    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    private User teacher;

public  Assignment(){

}
    public Assignment(Integer assignmentId, String title, String description, LocalDate dueDate, User teacher) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.teacher = teacher;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
}
