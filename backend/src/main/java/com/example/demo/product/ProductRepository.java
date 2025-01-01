package com.example.demo.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Integer> {
    // no need for Optional for the list, because an empty
    //category would return a list of size 0, not null
    @Query("{'category' : ?0}")
    List<Product> findByCategory(String category);

    @Query("{'id' : ?0}")
    Optional<Product> findById(String id);

}
