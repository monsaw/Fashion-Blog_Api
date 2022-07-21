package com.example.fashionblog.repository;

import com.example.fashionblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Optional<Blog> findById(Integer blogId);


    List<Blog> findAllByAdmin_NameOrderByCreateDesc(String name);

    List<Blog> findAllByCategory_CategoryNameOrderByCreateDesc(String name);

//    List<Blog> findAllByBlogPostOrderByCreateDesc();




}
