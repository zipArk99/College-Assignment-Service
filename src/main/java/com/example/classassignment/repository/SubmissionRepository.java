package com.example.classassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.classassignment.model.Submission;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}
