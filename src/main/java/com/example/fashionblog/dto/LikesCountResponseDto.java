package com.example.fashionblog.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class LikesCountResponseDto {

    private Integer blogId;

    private String blogPost;

    private String imageUrl;

    private String description;

    private String categoryName;

    private String Total;
}
