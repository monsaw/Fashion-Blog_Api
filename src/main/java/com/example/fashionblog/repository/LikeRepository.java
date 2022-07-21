package com.example.fashionblog.repository;

import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.entity.Like;
import com.example.fashionblog.enums.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByRateAndBlogId(Rate rate , Integer blogId);
    Like findByRateAndCustomerAndBlog(Rate rate, Customer customer, Blog blog);
    Like findByCustomerAndBlog(Customer customer, Blog blog);
}
