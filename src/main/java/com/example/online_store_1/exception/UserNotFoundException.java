package com.example.online_store_1.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String format) {
        super(format);
    }
}
