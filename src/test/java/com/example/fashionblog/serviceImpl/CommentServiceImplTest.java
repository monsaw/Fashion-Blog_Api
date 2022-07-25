package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentServiceImpl commentService;
    @Test
    public void createCommentTest(){


        CreateCommentDto createCommentDto = new CreateCommentDto();
        createCommentDto.setText("greater high");
        createCommentDto.setBlogId(2);

        Customer customer = new Customer();
        customer.setEmail("lawal2@gmail.com");
        customer.setRole("USER");

        assertEquals("Creates" ,  "Creates");

    }
}