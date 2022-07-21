package com.example.fashionblog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String blogPost;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false)
   private LocalDateTime create;


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "products")
    private List<Customer> customers;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "blog")
//    @JoinColumn(name = "product_id")

    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "blog")
    private List<Like> likes;

    @ManyToOne(cascade = CascadeType.ALL)
    private Admin admin;
}
