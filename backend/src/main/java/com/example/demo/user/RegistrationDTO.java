package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    @NotBlank(message = "Blank First Name")
    private String fname;
    @NotBlank(message = "Blank Last Name")
    private String lname;
    @NotBlank(message = "Blank Password")
    // Use the @Pattern to force, 1 upper, 1 lower, 8 characters, 1 special character
    private String password;
    @Email(message = "Not in format of email")
    private String email;


}
