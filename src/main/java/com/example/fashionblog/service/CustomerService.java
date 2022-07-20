package com.example.fashionblog.service;


import com.example.fashionblog.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    String create(CustomerCreateDto adminCreateDto);

    String loginCustomer(CustomerLoginDto customerLoginDto);

    String createComment(CreateCommentDto createCommentDto);

    String logoutCustomer();
}
