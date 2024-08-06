package com.example.capstone1_amazoneclone.Controller;


import com.example.capstone1_amazoneclone.Api.ApiResponse;
import com.example.capstone1_amazoneclone.Model.MerchantStock;
import com.example.capstone1_amazoneclone.Model.Product;
import com.example.capstone1_amazoneclone.Service.MerchantService;
import com.example.capstone1_amazoneclone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    // Dependency injection
    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;


                                     // ----- CRUD -----

    @GetMapping("/get")// get All Merchant Stocks
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStockList();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")// creat New Merchant Stock
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(201).body("Merchant Stock added successfully");
    }


    @DeleteMapping("/delete/{id}")// Delete Merchant Stock by id
    public ResponseEntity deleteMerchantStock(@PathVariable String id) {

        if(merchantStockService.removeMerchantStock(id)){
            return ResponseEntity.status(200).body("Merchant Stock deleted successfully");
        }
        return ResponseEntity.status(404).body(" Sorry Merchant Stock Not Found");
    }

    @PutMapping("/update/{id}")//Update Merchant Stock by id
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
         if (merchantStockService.updateMerchantStock(id,merchantStock)){
             return ResponseEntity.status(201).body("Merchant Stock updated successfully");
         }
         return ResponseEntity.status(404).body(" Sorry Merchant Stock Not Found");
    }



                                           // ------ End Point --------

    @PutMapping("/addStock/{productId}/{merchantId}/{amount}") //user can add more stocks of product to a merchant Stock
    public ResponseEntity UserAddStocks(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int amount ) {

         if (amount <= 0) {
             return ResponseEntity.badRequest().body("Amount must be greater than zero");
         }

        boolean addStock = merchantStockService.addStockToMerchant(productId, merchantId, amount);

        if (addStock) {
            return ResponseEntity.status(201).body(new ApiResponse("Additional stock added successfully to Merchant Stock"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(" Sorry Failed Process ,Check if the product and merchant IDs are correct."));
        }

        /*
         merchantService.addStockToMerchantStock(productId, merchantId, additionalStock);
         */
    }


    @GetMapping("/product-merchant/{productId}/{merchantstockId}") // get merchant Stock by id
    public ResponseEntity getMerchantStock(@PathVariable String productId,@PathVariable String merchantstockId) {
        MerchantStock merchantStock=merchantStockService.getProductAndMerchant(productId,merchantstockId);

        if(merchantStock==null){
            return ResponseEntity.status(404).body(new ApiResponse(" Merchant Stock Not Found"));
        }
        return ResponseEntity.status(200).body(merchantStock);
    }




}
