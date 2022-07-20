package com.example.fashionblog.dto;


import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
public class AllCommentedBlogDto {


    private Integer id;
    private String comment;

    private String blogHeading;

    private String customer;

    private LocalDateTime time;

}
