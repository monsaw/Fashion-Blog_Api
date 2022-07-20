package com.example.fashionblog.service;


import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.entity.Comment;
import com.example.fashionblog.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void create(CreateCommentDto createCommentDto, Customer customer);

    List<?> findAllByBlogId(Integer blogId);

}
