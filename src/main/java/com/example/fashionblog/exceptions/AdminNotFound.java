package com.example.fashionblog.exceptions;

public class AdminNotFound extends RuntimeException{
    public AdminNotFound(String message) {
        super(message);
    }
}
