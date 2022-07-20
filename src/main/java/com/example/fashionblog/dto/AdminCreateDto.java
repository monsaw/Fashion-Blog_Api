package com.example.fashionblog.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdminCreateDto {
    private String email;
    private String name;
    private String password;

}
