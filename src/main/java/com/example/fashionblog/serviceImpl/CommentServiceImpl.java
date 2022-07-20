package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Comment;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.repository.BlogRepository;
import com.example.fashionblog.repository.CommentRepository;
import com.example.fashionblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }


    @Override
    public void create(CreateCommentDto createCommentDto, Customer customer) {
        Integer blogId = createCommentDto.getBlogId();
        String text = createCommentDto.getText();

        Blog blog = blogRepository.findById(blogId).get();
        Comment comment = Comment.builder()
                .blog(blog)
                .text(text)
                .customer(customer)
                .build();
        commentRepository.save(comment);




    }
}
