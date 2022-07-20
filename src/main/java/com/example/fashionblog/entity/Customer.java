package com.example.fashionblog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

//@Table(name="customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 45)
    private String email;
    @Column(nullable = false)
    private String password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")

    private List<Comment> comments;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Blog> products;

    @ManyToMany(cascade = CascadeType.ALL)

    private List<Like> likes;

}
