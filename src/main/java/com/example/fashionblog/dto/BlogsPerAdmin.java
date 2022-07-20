package com.example.fashionblog.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class BlogsPerAdmin {

    private String blogPost;

    private String imageUrl;

    private String description;

    private LocalDateTime create;

    private String Owner;
}
