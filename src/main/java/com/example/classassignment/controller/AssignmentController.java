package com.example.classassignment.controller;

import com.example.classassignment.model.Assignment;
import com.example.classassignment.model.AssignmentUpdateRequest;
import com.example.classassignment.service.AssignmentServiceInterface;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user/teacher")
public class AssignmentController {
    @Autowired
    private AssignmentServiceInterface assignmentService;

    @PostMapping(value = "/assignment/create", consumes = "application/json")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment request) {
        Assignment newAssignmnet = assignmentService.createAssignment(request);
        return ResponseEntity.ok(newAssignmnet);
    }

    @GetMapping("/getassignments")
    public ResponseEntity<List<Assignment>> getAllAssignmentByTeacherId(@RequestParam(name = "teacherId") Integer teacherId) {
        List<Assignment> lstAssignments = assignmentService.getAssignmentByTeacherId(teacherId);
        return ResponseEntity.ok(lstAssignments);
    }

    @DeleteMapping("/deleteassignment")
    private ResponseEntity<String> deleteAssignment(@RequestParam(name = "assignmentId") Integer assignmentId,@RequestParam(name = "teacherId") Integer teacherId) {
        String message = assignmentService.deleteAssignmnet(assignmentId,teacherId);
        return ResponseEntity.of(Optional.of(message));
    }

    @PutMapping("/assignment/update")
    public ResponseEntity<Assignment> updateAssignment(@RequestParam(name ="teacherId")Integer teacherId ,@RequestBody AssignmentUpdateRequest request){
        Assignment assignment=assignmentService.updateAssignment(teacherId,request.getAssignmentId(),request.getTitle(),request.getDescription(),request.getDueDate());
       return ResponseEntity.ok(assignment);
    }



}
