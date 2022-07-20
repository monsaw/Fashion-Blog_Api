package com.example.fashionblog.repository;

import com.example.fashionblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByBlogIdOrderByTime(Integer blogId);
}
