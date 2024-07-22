package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor


/**
 * Model Class of User
 * Database to Java Representation of a table value
 */
public class User extends Base {

    private String fname;
    private String lname;

    private String password;
    private String email;
    private String authority = "READ";

    public User(String email, String password, String lname, String fname) {

        this.email = email;
        this.password = password;
        this.lname = lname;
        this.fname = fname;
    }
}
