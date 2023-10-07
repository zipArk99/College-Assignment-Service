package com.example.classassignment.repository;

import com.example.classassignment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
    public List<Assignment> findAllByTeacherId(Integer teacherId);

    @Modifying
    @Query("UPDATE Assignment a SET a.title = :title, a.description = :description, a.dueDate = :dueDate WHERE a.assignmentId = :assignmentId")
     int updateAssignment(
            @Param("assignmentId") Integer assignmentId,
            @Param("title") String title,
            @Param("description") String description,
            @Param("dueDate") LocalDate dueDate
    );
}
