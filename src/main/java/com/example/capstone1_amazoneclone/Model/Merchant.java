package com.example.capstone1_amazoneclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotEmpty(message = " Merchant Id must not be Empty ")
    private String id ;

    @NotEmpty(message = " Merchant Name have not be Empty ")
    @Size(min = 4 ,message = " Merchant Name must be more than 3 character ")
    private String name;


}
