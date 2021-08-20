package com.learning.fun.jokes.validation;

import com.learning.fun.jokes.model.Category;

public class CategoryValidator {

    public boolean isValidCategory(String givenCategory){
        boolean isValid = false;
        Category[] acceptedCategories = Category.values();
        for (Category category: acceptedCategories) {
            if(category.toString().equals(givenCategory.toUpperCase())){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
