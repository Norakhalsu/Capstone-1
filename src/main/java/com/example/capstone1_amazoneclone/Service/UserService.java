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
