package com.example.classassignment.service;

import com.example.classassignment.exception.AssignmentException;
import com.example.classassignment.exception.NotAuthorizedUser;
import com.example.classassignment.exception.UserException;
import com.example.classassignment.model.Assignment;
import com.example.classassignment.model.User;
import com.example.classassignment.repository.AssignmentRepository;
import com.example.classassignment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Assignment createAssignment(Assignment assignment) {
        User teacher = userRepository.findById(assignment.getTeacher().getId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + assignment.getTeacher().getId()));
        assignment.setTeacher(teacher);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAssignmentByTeacherId(Integer teacherId) {
        List<Assignment> assignments = assignmentRepository.findAllByTeacherId(teacherId);
        System.out.println(assignments);
        return assignments;
    }

    @Override
    public String deleteAssignmnet(Integer assignmentId, Integer teacherId) {

        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (assignment.isEmpty()) {
            throw new AssignmentException("No assignment Found With Id ::" + assignmentId);
        }
        if (userRepository.findById(teacherId).isEmpty()) {
            throw new UserException("No teacher found with Id::" + teacherId);
        } else if (!teacherId.equals(assignment.get().getTeacher().getId())) {
            throw new NotAuthorizedUser("Only creator of assignment can update Assignment");
        }
        assignmentRepository.deleteById(assignmentId);
        return "Assignment " + assignmentId + " deleted successfully";

    }

    @Override
    public List<Assignment> getAllAssignments() {

        return null;
    }


    @Override
    @Transactional
    public Assignment updateAssignment(Integer teacherId, Integer assignmentId, String title, String description, LocalDate dueDate) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (userRepository.findById(teacherId).isEmpty()) {
            throw new UserException("No user with this id found");
        } else if (assignment.isEmpty()) {
            throw new AssignmentException("No Assignment with this id found");
        } else if (!teacherId.equals(assignment.get().getTeacher().getId())) {
            throw new NotAuthorizedUser("Only creator of assignment can update Assignment");
        }
        int updatedRows = assignmentRepository.updateAssignment(assignmentId, title, description, dueDate);
        return assignment.orElse(null);

    }

}
