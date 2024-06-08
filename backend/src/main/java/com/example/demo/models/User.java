package com.example.demo.models;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Data


@Document("User")
public class User extends Base {

    private String username;
    private String email;
    private String password;


    public User(String username, String password) {
        this.username =username;
        this.password = password;
    }
}
