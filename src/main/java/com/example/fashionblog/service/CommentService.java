package com.example.fashionblog.service;


import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void create(CreateCommentDto createCommentDto, Customer customer);

}
