package com.example.fashionblog.service;


import com.example.fashionblog.dto.AllBlogResponseDto;
import com.example.fashionblog.dto.BlogCreateDto;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.entity.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
        String createBlog(BlogCreateDto blogCreateDto, Admin admin);

        String deleteBlog(Integer blogId);

        List<AllBlogResponseDto> findAllBlog();

        List<AllBlogResponseDto> findAllBlogByAdmin(String name);

        List<AllBlogResponseDto> findAllBlogByCategory(String name);
}
