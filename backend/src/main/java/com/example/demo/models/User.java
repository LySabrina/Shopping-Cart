package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("User")
/**
 * Model Class of User
 * Database to Java Representation of a table value
 */
public class User extends Base {

    private String username;

    private String password;
    private String email;
    private String authority;


}
