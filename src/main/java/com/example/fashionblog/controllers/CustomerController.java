package com.example.fashionblog.controllers;


import com.example.fashionblog.dto.*;
import com.example.fashionblog.serviceImpl.CategoryServiceImpl;
import com.example.fashionblog.serviceImpl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    public final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;

    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> customerSignUp(@RequestBody CustomerCreateDto customerCreateDto){
        return ResponseEntity.ok(customerService.create(customerCreateDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> customerLogin(@RequestBody CustomerLoginDto customerLoginDto){
        return ResponseEntity.ok(customerService.loginCustomer(customerLoginDto));
    }
    @GetMapping("/logout")
    public ResponseEntity<String> customerLogout(){
        return ResponseEntity.ok(customerService.logoutCustomer());
    }

    @PostMapping("/comment")
    public ResponseEntity<String> customerComment(@RequestBody CreateCommentDto createCommentDto){
        return ResponseEntity.ok(customerService.createComment(createCommentDto));
    }

    @PostMapping("/like")
    public ResponseEntity<String> customerComment(@RequestBody LikeCreateDto likeCreateDto){
        return ResponseEntity.ok(customerService.createLike(likeCreateDto));
    }
}
