package com.example.capstone1_amazoneclone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = " User Id must not be Empty ")
    private String id;

    @NotEmpty(message = "User Name must not be Empty ")
    @Size(min = 6,message = " User Name must be more than 5 character ")
    private String username;


    @NotEmpty(message = "User Password must not be Empty ")
    @Size(min = 7 , message = " User Password must be more than 6 character ")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least one letter and one digit (no Symbols) ")
    private String password;

    @NotEmpty(message = "Phone Number is require ")
    private String phoneNumber;

    @NotEmpty(message = "User Email must not be Empty ")
    @Email(message = "User Email must be in Email Format ")
    private String email;

    @Size(min = 5,max = 10 ,message = " Role length must be 5-10 character ")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")    @NotEmpty(message = " User Role must not be Empty ")
    private String role;

    @NotNull(message = " User Balance must be not empty ")
    @Positive(message = "User Balance must be a Positive ")
    @Min(value = 0)
    private double balance;

    private int score;



}
