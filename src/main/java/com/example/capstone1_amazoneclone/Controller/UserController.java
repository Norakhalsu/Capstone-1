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



                              // ----- Extra End Point ------

    @GetMapping("/card/{userId}/{productPrice}")
    public ResponseEntity gift(@PathVariable String userId, @PathVariable double productPrice) {
        String giftMessage = userService.giftCard(userId, productPrice);

        if (giftMessage.equals("You have not spent enough to qualify for a gift card.")) {
            return ResponseEntity.status(400).body(new ApiResponse("Gift Card Empty"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(giftMessage));
    }



    @GetMapping("/log-in/{phoneNumber}") // User login with PhoneNumber and get a verification Code
    public ResponseEntity logIn(@PathVariable String phoneNumber) {
        String verificationResult = userService.login(phoneNumber);
        if (verificationResult.equals("Phone number is not correct")) {
            return ResponseEntity.status(400).body(new ApiResponse("Sorry, User Not Found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse(verificationResult));
    }



     @GetMapping("/support-team/{username}/{description}")
     public ResponseEntity supportTeam(@PathVariable String username, @PathVariable String description) {

        if(userService.connectToSupportCenter(username,description)== null){

            return ResponseEntity.status(400).body(new ApiResponse("Sorry, User Not Found"));
        }
        return ResponseEntity.status(200).body(userService.connectToSupportCenter(username,description));
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
