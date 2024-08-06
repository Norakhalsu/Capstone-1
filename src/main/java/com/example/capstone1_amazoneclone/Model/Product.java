package com.example.capstone1_amazoneclone.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = " Product id must not be null ")
    private String id;

    @NotEmpty(message = " Product Name must not be  Empty ")
    @Size(min = 4, message = "Product Name must be more than 3 character ")
    private String productName;

    @NotNull(message = " Price must not be Empty ")
    @Positive(message = "Price must be positive number ")
    @Min(value = 0)
    private double price;

    @NotEmpty(message = " Category Id must not be Empty  ")
    private String categoryId;

    @Min(value = 0, message = "Number of purchases must be zero or positive")
    private int numberOfPurchases = 0; // Add this field


}
