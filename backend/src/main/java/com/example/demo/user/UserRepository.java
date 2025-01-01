package com.example.demo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {


    @Query("{'email' : ?0}")
    Optional<User> findByEmail(String email);


}