package com.example.demo.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String email) {
        super("User w/email: " + email + " not found");
    }
}
