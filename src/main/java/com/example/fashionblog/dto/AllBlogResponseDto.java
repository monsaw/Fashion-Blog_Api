package com.example.fashionblog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
public class AllBlogResponseDto {

    private Integer blogId;

    private String blogPost;

    private String imageUrl;

    private String description;

    private String categoryName;

    private LocalDateTime create;

    private String blogCreator;
}
