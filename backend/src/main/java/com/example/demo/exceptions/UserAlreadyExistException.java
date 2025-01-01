package com.example.demo.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String user) {
        super("User: " + user + " already exist");
    }
}
