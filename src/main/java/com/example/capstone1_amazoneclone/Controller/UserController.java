package com.example.capstone1_amazoneclone.Controller;


import com.example.capstone1_amazoneclone.Api.ApiResponse;
import com.example.capstone1_amazoneclone.Model.User;
import com.example.capstone1_amazoneclone.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

                                      // --- CRUD -------

    @GetMapping("/get")// get All Users
    public ResponseEntity getUser() {
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }


    @PostMapping("/add") // Creat a new User
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body(" User added successfully");
    }

    @PutMapping("/update/{id}")// update user by id
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.updateUser(id, user)) {
            return ResponseEntity.status(200).body(" User updated successfully");
        }
        return ResponseEntity.status(400).body(" Sorry User Not Found");
    }


    @DeleteMapping("/delete/{id}")// Delete User By id
    public ResponseEntity deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(200).body(" User deleted successfully");
        }
        return ResponseEntity.status(400).body(" Sorry User Not Found");
    }

    // get user by id
    @GetMapping("/get-user/{userid}")
    public ResponseEntity getUserById(@PathVariable String userid) {

        User user = userService.getUserById(userid);
        if (user != null) {
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(400).body(new ApiResponse(" Sorry User Not Found"));
    }



}
