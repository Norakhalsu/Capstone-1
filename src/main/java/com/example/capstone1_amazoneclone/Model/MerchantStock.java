package com.example.capstone1_amazoneclone.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Merchant Stock Id must not be Empty ")
    private String id;

    @NotEmpty(message = " Product Id must not be Empty ")
    private String productId;

    @NotEmpty(message = " Merchant Id must not be Empty ")
    private String merchantId;

    @NotNull(message = "  Stock must not be Empty ")
    @Min(value = 11 , message = "  Stock have to be more than 10 at start ")
    private Integer stock;


}
