package com.example.classassignment.service;

import com.example.classassignment.model.Assignment;
import com.example.classassignment.repository.AssignmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssignmentServiceInterface {

    /*○ Creates new assignment with data provided in the request body
○ Returns all assignments based on data provided in the request body
○ Updates assignment with data provided in the request body
○ Deletes the assignment*/

    public Assignment createAssignment(Assignment assignment);

    public List<Assignment> getAssignmentByTeacherId(Integer teacherId);

    public String deleteAssignmnet(Integer assignmentId,Integer teacherId);

    public List<Assignment> getAllAssignments();

    public Assignment updateAssignment(Integer teacherId,Integer assignmentId, String title, String description, LocalDate dueDate);

}