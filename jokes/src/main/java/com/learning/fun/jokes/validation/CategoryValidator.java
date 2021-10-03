package com.learning.fun.jokes.validation;

import com.learning.fun.jokes.model.Category;

public class CategoryValidator {

    public boolean isValidCategory(String givenCategory){
        boolean isValid = false;
        isValid = Category.isValid(givenCategory);
        return isValid;
    }
}
