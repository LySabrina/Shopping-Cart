package com.example.demo.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
/**
 * Model Class of User
 * Database to Java Representation of a table value
 */
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
