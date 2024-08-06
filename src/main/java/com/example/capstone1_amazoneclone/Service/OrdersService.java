package com.example.capstone1_amazoneclone.Service;


import com.example.capstone1_amazoneclone.Model.Orders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrdersService {

    ArrayList<Orders> orders = new ArrayList<Orders>();


                            // ----- Extra End Point -------

    // method to generate Order Number to Track
    public  static int generateOrderNumber() {
        // logic to generate a track number
        return (int) (Math.random() * 100000);
    }


    public Orders getLatestOrder() {
        if (orders.isEmpty()) {
            return null; // Handle the case when there are no orders
        } else {
            return orders.get(orders.size() - 1); // Return the latest order from the list
        }
    }



}
