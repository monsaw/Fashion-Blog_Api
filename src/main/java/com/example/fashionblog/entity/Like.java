package com.example.fashionblog.entity;


import com.example.fashionblog.enums.Rate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private Rate rate;

    @ManyToOne(cascade = CascadeType.ALL ,fetch=FetchType.LAZY)
    private Blog blog;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;


}

