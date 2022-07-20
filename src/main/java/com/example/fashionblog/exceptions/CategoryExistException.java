package com.example.fashionblog.exceptions;

public class CategoryExistException extends RuntimeException{
    public CategoryExistException(String message) {
        super(message);
    }
}
