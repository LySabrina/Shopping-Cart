package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository =productRepository;
    }

    public Product findById(String id){
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }
}
