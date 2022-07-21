package com.example.fashionblog.service;

import com.example.fashionblog.dto.LikeCreateDto;
import com.example.fashionblog.dto.LikesCountResponseDto;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.enums.Rate;

import java.util.List;

public interface LikeService {
    String create(LikeCreateDto likeCreateDto, Customer customer , Blog blog);

    LikesCountResponseDto getLikeCount(Rate rate, Integer blogId);

}
