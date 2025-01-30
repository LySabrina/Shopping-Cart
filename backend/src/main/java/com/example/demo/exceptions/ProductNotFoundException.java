package com.example.demo.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String id) {
        super("Product of id: " + id + " is not found");
    }
}
