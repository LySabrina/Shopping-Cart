package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fname;
    private String lname;
    private String password;
    private String email;

    public UserDTO(String email, String password){
        this.email = email;
        this.password = password;
    }



}
