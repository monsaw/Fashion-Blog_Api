package com.example.fashionblog.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCreateDto {

    private String blogPost;

    private String imageUrl;

    private String description;

    private String categoryName;

}
