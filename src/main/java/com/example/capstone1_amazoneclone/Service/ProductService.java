package com.example.capstone1_amazoneclone.Service;

import com.example.capstone1_amazoneclone.Model.*;
import com.sun.source.tree.BreakTree;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> WishList = new ArrayList<>();


    // Dependency Injections
    @Autowired
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;


                                           //-----  CRUD Methods   --------

    // get All Product
    public ArrayList<Product> getProducts() {

        return products;
    }


    // creat new Product
    public void addProducts(Product product) {
        products.add(product);
    }


    // Delete Product by id
    public boolean removeProducts(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


    // update product by id
    public boolean updateProducts(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }


                                                  // -----  End Point -------

    //Create endpoint where user can buy a product directly
    public String UserBuyProduct(String userId, String productId, String merchantId) {

        User user = userService.getUserById(userId);
        if (!userId.equals(userId)) {
            return "User not found";
        }
        Product product = getProductById(productId);
        if (product == null) {
            return "Product not found";
        }

        MerchantStock merchantStock = merchantStockService.getProductAndMerchant(productId, merchantId);
        if (merchantStock == null) {
            return "Merchant Not Found ";
        }

        if (merchantStock.getStock() <= 0) {
            return "product out of Stock";
        }

        if (user.getBalance() < product.getPrice()) {
            return "Insufficient balance";
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        user.setBalance(user.getBalance() - product.getPrice());
        return "Purchase successful.";
    }


                                              // --- Extra End Points -----


    // Costumer Have Gift Card when buy with determine price
    public String giftCard(String userId, String productId, String merchantId) {

        if (UserBuyProduct(userId, productId, merchantId).equals("Purchase successful.")) {

            User user = userService.getUserById(userId);
            Product product = getProductById(productId);
            Merchant merchant=merchantService.getMerchantById(merchantId);

            if (user == null){
                return "User not found";
            }

            if (product == null) {
                return "Product not found";
            }

            if (merchant == null) {
                return "Merchant not found";
            }

            double totalSpent =  product.getPrice() - user.getBalance();

            if (totalSpent >= 1000) {
                return "Congratulations, you have a gift card with 600 Riyal!";
            } else if (totalSpent >= 800) {
                return "Congratulations, you have a gift card with 500 Riyal!";
            } else if (totalSpent >= 500) {
                return  "Congratulations, you have a gift card with 200 Riyal!";
            } else {
                return  "You have not spent enough to qualify for a gift card.";
            }
        } else {
            return  "Indicate that the purchase was not successful" ;
        }
    }



        // User want Tracking Order
        public String trackOrderMethod(String userId, String password, String productId, String merchantId) {
            ArrayList<Product> orders = new ArrayList<>();
            String purchaseResult = UserBuyProduct(userId, productId, merchantId);

            if (!purchaseResult.equals("Purchase successful.")) {
                return "Indicate that the purchase was not successful";
            }
            User user = userService.getUserById(userId);
            if (user == null) {
                return "User not found";
            }

            boolean checkPassword = password.equals(user.getPassword());
            if (!checkPassword) {
                return "Incorrect password";
            }
            orders.add(getProductById(productId));

            if (orders.isEmpty()) {
                return "No orders available to track";
            }

            int orderNumber = generateOrderNumber();
            LocalDate expectedDeliveryDate = LocalDate.now().plusDays(5);
            return "Track number: " + orderNumber + ", Expected Arrival Date: " + expectedDeliveryDate;
        }
        // get product by id
        public Product getProductById (String id){
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(id)) {
                    return products.get(i);
                }
            }
            return null;
        }




        // User add product to wish list
        public String addProductToWishList (String userId, String productId){

            User user = userService.getUserById(userId); // check if user id found
            if (user == null) {
                return " User Id not found ";
            }

            Product product = getProductById(productId); // check if product id is found
            if (product == null) {
                return " Product Id not found ";
            }

            if (WishList.contains(product)) {
                return "Product is already in your wishlist"; // Product already exists in the wishlist
            }

            WishList.add(product); // Add Product to wish List
            user.setScore(user.getScore() + 1); // Increase user's score // when user added product to wish list , Score is increase
            return " Product added successfully , You have One Point Score !! ";
        }


        // Complete : first check if user found , then User want to get his WishList
        public ArrayList<Product> getWishList (String userId){
            User user = userService.getUserById(userId);
            if (user == null) {
                return null;
            }
            return WishList;
        }

    public int generateOrderNumber() {
        // logic to generate a track number
        return (int) (Math.random() * 100000);
    }
    
}


