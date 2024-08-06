package com.example.capstone1_amazoneclone.Service;


import com.example.capstone1_amazoneclone.Model.Merchant;
import com.example.capstone1_amazoneclone.Model.MerchantStock;
import com.example.capstone1_amazoneclone.Model.Product;
import com.example.capstone1_amazoneclone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {


//    private final ProductService productService;
    ArrayList<MerchantStock> merchantStockList = new ArrayList<MerchantStock>();




                           // -------- CRUD --------

    // get All Merchant Stock
    public ArrayList<MerchantStock> getMerchantStockList() {

        return merchantStockList;
    }

    // Creat new Merchant Stocks
    public void addMerchantStock(MerchantStock merchantStock) {

        merchantStockList.add(merchantStock);
    }

    // Delete Merchant Stock by id
    public boolean removeMerchantStock(String id) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId().equals(id)) {
                merchantStockList.remove(i);
                return true;
            }
        }
        return false;
    }


      // update Merchant Stock by id
     public boolean updateMerchantStock(String id ,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStockList.size(); i++) {
            if (merchantStockList.get(i).getId().equals(id)) {
                merchantStockList.set(i,merchantStock);
                return true;
            }
        }
            return false;
  }



                                             // ------ End Point -----

      //user can add more stocks of product to a merchant Stock
      public boolean addStockToMerchant(String productId, String merchantId, int amount) {
        for (MerchantStock stock : merchantStockList) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                int newStock = stock.getStock() + amount;
                stock.setStock(newStock);
                return true;
            }
        }
        return false;
    }



    //  get Product and Merchant by id
    public MerchantStock getProductAndMerchant(String productId, String merchantId) {
        for (MerchantStock stock : merchantStockList) {
            if (stock.getProductId().equals(productId) && stock.getMerchantId().equals(merchantId)) {
                return stock;
            }
        }
        return null;
    }






}
