package com.example.capstone1_amazoneclone.Controller;


import com.example.capstone1_amazoneclone.Model.Category;
import com.example.capstone1_amazoneclone.Model.Product;
import com.example.capstone1_amazoneclone.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {


    //Dependency injection
    private final CategoryService categoryService;


                                              // ---- CRUD ----

    @GetMapping("/get") //get All Category
    public ResponseEntity getCategories() {
        ArrayList<Category> categories = categoryService.getCategories();
        return  ResponseEntity.status(200).body(categories);
    }


    @PostMapping("/add") // creat new Category
    public ResponseEntity addCategories(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        categoryService.addCategories(category);
        return ResponseEntity.status(200).body(" Category Added Successfully");
    }


    @DeleteMapping("/delete/{id}")// Delete Category by id
    public ResponseEntity deleteProduct(@PathVariable String id) {

        if (categoryService.removeCategory(id)){
            return ResponseEntity.status(200).body(" Category Deleted Successfully");
        }
        return ResponseEntity.status(404).body(" Category Not Found");
    }


    @PutMapping("/update/{id}")// update Category by id
    public ResponseEntity putCategory(@PathVariable String id, @Valid@RequestBody Category category , Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        if (categoryService.updateCategory(id, category)){
            return ResponseEntity.status(200).body(" Category Updated Successfully");
        }
        return ResponseEntity.status(404).body(" Category Not Found");
    }



    @GetMapping("/get-category/{categoryId}") // get category by id
    public ResponseEntity getCategory(@PathVariable String categoryId) {
        Category category=categoryService.getCategoryById(categoryId);

        if(category==null){
            return ResponseEntity.status(404).body(" Category Not Found");
        }
        return ResponseEntity.status(200).body(category);
    }


}
