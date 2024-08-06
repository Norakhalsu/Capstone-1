package com.example.capstone1_amazoneclone.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Orders {

    private String userId;
    private String productId;
    private LocalDate expectedDate;

}
