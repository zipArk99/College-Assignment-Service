package com.example.classassignment.exception;

public class NotAuthorizedUser extends RuntimeException{

    public NotAuthorizedUser(String message) {
        super(message);
    }

    public NotAuthorizedUser(String message, Throwable cause) {
        super(message, cause);
    }
}
