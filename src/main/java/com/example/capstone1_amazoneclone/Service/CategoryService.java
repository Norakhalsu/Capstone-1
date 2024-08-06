package com.example.capstone1_amazoneclone.Service;

import com.example.capstone1_amazoneclone.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<Category>();


                   // -----  CRUD  ---------

    // get All Categories
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // creat new Category
    public void addCategories(Category category) {
        categories.add(category);
    }

    // Delete Category by id
    public boolean removeCategory(String id ) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                categories.remove(category);
                return true;
            }
        }
        return false;
    }


     // Update Category by id
    public boolean updateCategory(String id ,Category category) {
        for (int i = 0; i < categories.size() ; i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }



    // get category by id
    public Category getCategoryById(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }




}
