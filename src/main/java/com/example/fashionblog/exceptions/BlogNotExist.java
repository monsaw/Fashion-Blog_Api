package com.example.fashionblog.exceptions;

public class BlogNotExist extends RuntimeException{

    public BlogNotExist(String message) {
        super(message);
    }
}
