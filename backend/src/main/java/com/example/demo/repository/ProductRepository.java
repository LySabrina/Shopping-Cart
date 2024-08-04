package com.example.demo.repository;

import com.example.demo.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    @Query("{'category' : ?0}")
    List<Product> findByCategory(String category);

    @Query("{'id' : ?0}")
    Product findById(String id);

}
