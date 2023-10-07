package com.example.classassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(value = {AssignmentException.class})
    public ResponseEntity<Object> handleAssignmentException(AssignmentException assignmentException) {
        GlobalExceptionModel globalExceptionModel = new GlobalExceptionModel(
                assignmentException.getMessage(),
                assignmentException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(globalExceptionModel, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException authenticationException) {
        GlobalExceptionModel globalExceptionModel = new GlobalExceptionModel(
                authenticationException.getMessage(),
                authenticationException.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(globalExceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handleUserException(UserException userException) {
        GlobalExceptionModel globalExceptionModel = new GlobalExceptionModel(
                userException.getMessage(),
                userException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(globalExceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotAuthorizedUser.class})
    public ResponseEntity<Object> handleNotAuthorizedUserException(NotAuthorizedUser notAuthorizedUser) {
        GlobalExceptionModel globalExceptionModel = new GlobalExceptionModel(
                notAuthorizedUser.getMessage(),
                notAuthorizedUser.getCause(),
                HttpStatus.UNAUTHORIZED
        );
        return new ResponseEntity<>(globalExceptionModel, HttpStatus.NOT_FOUND);
    }
}
