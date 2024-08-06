package com.example.capstone1_amazoneclone.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = " Category Id must not Empty ")
    private String id;

    @Size(min = 4 , message = " Category Name must more than 3 character ")
    @NotEmpty(message = " Category Name must not be empty ")
    private String name;


}
