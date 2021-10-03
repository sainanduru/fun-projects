package com.learning.fun.jokes.exception;

public class CustomException extends Exception{

    private static final long serialVersionUID = -7661881974219233121L;

    public CustomException(String message){
        super(message);
    }
}
