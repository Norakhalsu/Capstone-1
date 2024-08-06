package com.example.capstone1_amazoneclone.Service;


import com.example.capstone1_amazoneclone.Model.Orders;
import com.example.capstone1_amazoneclone.Model.Product;
import com.example.capstone1_amazoneclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    ArrayList<User> users = new ArrayList<User>();
//    Product product;


                                      // ------- CRUD --------

    // Get All Users
    public ArrayList<User> getUsers() {

        return users;
    }


    // creat new User
    public void addUsers(User user) {

        users.add(user);
    }

    // Update User by id
    public boolean updateUser(String id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }


    // delete user by id
    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }


                                      // ---------- Extra Point ----------

    // Costumer Have Gift when buy with determine price
    public String giftCard(String userId, double productPrice) {

        User user = getUserById(userId);

        if (user == null) {
            return "User not found";
        }

        double totalSpent = user.getBalance() - productPrice;

        if (totalSpent >= 1000) {
            return "Congratulations, you have a gift card with 600 Riyal!";
        } else if (totalSpent >= 800) {
            return "Congratulations, you have a gift card with 500 Riyal!";
        } else if (totalSpent >= 500) {
            return "Congratulations, you have a gift card with 200 Riyal!";
        } else {
            return "You have not spent enough to qualify for a gift card.";
        }
    }


                                            // ------ extra credit -------

    // user log in with phone number and get a verification Code
    public String login(String phoneNumber) {

        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                int verificationCode = (int) (Math.random() * 9000) + 1000; // Generate a 4-digit random verification code
                // Send verification code logic here
                return "Verification code: " + verificationCode;
            }
        }
        return "Phone number is not correct";
    }



//    public ArrayList<Orders> getOrders() {
//        return getOrders();
//    }



    // Costumer Have Problem and need to connect with support team
    public String connectToSupportCenter(String Name, String issueDescription) {

        String phoneNumberSupport = "0509875764";

        if (Name != null && !Name.isEmpty()) {
            return "Please contact our support team at phone number: " + phoneNumberSupport;
        }
        return "Error: User name is missing. Please provide a valid user name.";
    }


    // get user by id
    public User getUserById(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }



}