package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.AllCommentedBlogDto;
import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Comment;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.exceptions.BlogNotExist;
import com.example.fashionblog.repository.BlogRepository;
import com.example.fashionblog.repository.CommentRepository;
import com.example.fashionblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        Blog blog = blogRepository.findById(blogId).orElseThrow(()-> new BlogNotExist("No Such blog created, check " +
                "blog post and review"));
        Comment comment = Comment.builder()
                .blog(blog)
                .text(text)
                .customer(customer)
                .build();
        commentRepository.save(comment);

    }

    @Override
    public List<?> findAllByBlogId(Integer blogId) {
        List<Comment> comments = commentRepository.findAllByBlogIdOrderByTime(blogId);
        if (comments.size() < 1) return Collections.singletonList("No comment on this product!");
        List <AllCommentedBlogDto> allComment = new ArrayList<>();

        for (Comment commented : comments){
            AllCommentedBlogDto comment =  new AllCommentedBlogDto();
            comment.setBlogHeading(commented.getBlog().getBlogPost());
            comment.setId(commented.getId());
            comment.setCustomer(commented.getCustomer().getName());
            comment.setComment(commented.getText());
            comment.setTime(commented.getTime());
            allComment.add(comment);
        }
    return allComment;}
}
