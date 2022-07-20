package com.example.fashionblog.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerCreateDto {

    private String email;
    private String name;
    private String password;
}
