package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/api/category/{category}")
    public ResponseEntity<List<Product>> getProductsFromCategory(@PathVariable String category){
        //work in returning data to frontend
        return  null;
    }
}
