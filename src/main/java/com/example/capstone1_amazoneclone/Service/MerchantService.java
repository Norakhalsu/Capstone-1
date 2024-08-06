package com.example.capstone1_amazoneclone.Service;


import com.example.capstone1_amazoneclone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<Merchant>();


                  // --------- CRUD ---------------

    // get All Merchant
    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    // Creat new Merchant
    public void addMerchants(Merchant merchant) {
        merchants.add(merchant);
    }


    // Delete Merchant by id
    public boolean removeMerchant(String id ) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }


    // Update Merchant by id
    public boolean updateMerchant(String id ,Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }



     // get merchant by id
     public Merchant getMerchantById(String id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                return merchants.get(i);
            }
        }
        return null;
     }
}
