package com.example.classassignment.exception;

public class AssignmentException extends RuntimeException{


    public AssignmentException(String message) {
        super(message);
    }

    public AssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
