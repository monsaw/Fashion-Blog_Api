package com.example.fashionblog.dto;

import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
@Setter
@Getter
public class CreateCommentDto {

    private String text;

    private Integer blogId;

//    private Customer customer;
}
