package com.example.capstone1_amazoneclone.Controller;


import com.example.capstone1_amazoneclone.Api.ApiResponse;
import com.example.capstone1_amazoneclone.Model.Category;
import com.example.capstone1_amazoneclone.Model.Merchant;
import com.example.capstone1_amazoneclone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
public class MerchantController {

     // dependency injection
    private final MerchantService merchantService;


                                // --- CRUD -------

    @GetMapping("/get") //get All Category
    public ResponseEntity getMerchants() {
        ArrayList<Merchant> merchants = merchantService.getMerchants();
        return  ResponseEntity.status(200).body(merchants);
    }


    @PostMapping("/add") // creat new Merchant
    public ResponseEntity addMerchants(@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        merchantService.addMerchants(merchant);
        return ResponseEntity.status(200).body(" Merchant Added Successfully");
    }


    @DeleteMapping("/delete/{id}")// Delete Merchant by id
    public ResponseEntity removeMerchant(@PathVariable String id) {

        if (merchantService.removeMerchant(id)){
            return ResponseEntity.status(200).body(new ApiResponse(" Merchant Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse(" Sorry Merchant Not Found"));
    }


    @PutMapping("/update/{id}")// update Merchant by id
    public ResponseEntity updateMerchant(@PathVariable String id, @Valid@RequestBody Merchant merchant , Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (merchantService.updateMerchant(id, merchant)){
            return ResponseEntity.status(200).body(new ApiResponse(" Merchant Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse(" Sorry Merchant Not Found"));
    }


    @GetMapping("/get-merchant/{merchantId}")// get merchant by id
    public ResponseEntity getMerchant(@PathVariable String merchantId) {
        Merchant merchant = merchantService.getMerchantById(merchantId);

        if(merchant == null){
            return ResponseEntity.status(404).body(new ApiResponse(" Sorry Merchant Not Found"));
        }
        return  ResponseEntity.status(200).body(merchant);
    }

}
