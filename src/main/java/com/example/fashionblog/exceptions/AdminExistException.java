package com.example.fashionblog.exceptions;

public class AdminExistException extends RuntimeException{
    public AdminExistException(String message) {
        super(message);
    }
}
