package com.example.fashionblog.entity;


import com.example.fashionblog.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private Rate rate;

    @ManyToMany(cascade = CascadeType.ALL ,fetch=FetchType.LAZY, mappedBy = "likes")
    private List<Blog> blogs;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "likes")
    private List<Customer> customers;


}

