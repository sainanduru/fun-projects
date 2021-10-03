package com.learning.fun.jokes.model;

import java.util.Arrays;

public enum Category {
    CAREER, CELEBRITY, DEV, MUSIC, SPORT, SCIENCE;

    public static boolean isValid(String category){
        return Arrays.stream(Category.values()).anyMatch(value -> value.name().equals(category));
    }
}
