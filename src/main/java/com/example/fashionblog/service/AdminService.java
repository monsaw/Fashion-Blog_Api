package com.example.fashionblog.service;

import com.example.fashionblog.dto.*;
import com.example.fashionblog.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    String create(AdminCreateDto adminCreateDto);

    String loginAdmin(AdminLoginDto adminLoginDto);

    String logoutAdmin();

    String createCategory(CategoryCreateDto categoryCreateDto);

    String deleteCategory(CategoryDeleteDto categoryDeleteDto);

    String createBlog(BlogCreateDto blogCreateDto);

    String deleteBlog(Integer blogId);

    List<AllBlogResponseDto> allBlogs();

    List<AllBlogResponseDto> allBlogsByCat(String name);

    List<AllBlogResponseDto> allBlogsByCreator(String name);
}
