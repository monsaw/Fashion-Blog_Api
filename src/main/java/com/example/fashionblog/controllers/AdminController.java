package com.example.fashionblog.controllers;


import com.example.fashionblog.dto.*;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.enums.Rate;
import com.example.fashionblog.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<String> adminSignUp(@RequestBody AdminCreateDto adminCreateDto){
        return ResponseEntity.ok(adminService.create(adminCreateDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestBody AdminLoginDto adminLoginDto){
        return ResponseEntity.ok(adminService.loginAdmin(adminLoginDto));
    }
    @GetMapping("/logout")
    public ResponseEntity<String> adminLogout(){
        return ResponseEntity.ok(adminService.logoutAdmin());
    }

    @PostMapping("/create-category")
    public ResponseEntity<String> adminCreateCategory(@RequestBody CategoryCreateDto categoryCreateDto){
        return ResponseEntity.ok(adminService.createCategory(categoryCreateDto));
    }
    @DeleteMapping("/delete-category")
    public ResponseEntity<String> adminDeleteCategory(@RequestBody CategoryDeleteDto categoryDeleteDto){
        return ResponseEntity.ok(adminService.deleteCategory(categoryDeleteDto));
    }
    @PostMapping("/create-blog")
    public ResponseEntity<String> adminCreateBlogPost(@RequestBody BlogCreateDto blogCreateDto){
        return ResponseEntity.ok(adminService.createBlog(blogCreateDto));
    }

    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity<String> adminDeleteBlog(@PathVariable("id") Integer userId){
        return ResponseEntity.ok(adminService.deleteBlog(userId));
    }

    @GetMapping("/view-all-blog")
    public ResponseEntity<List<AllBlogResponseDto>> allBlogsPost(){
        return ResponseEntity.ok(adminService.allBlogs());
    }

    @GetMapping("/view-all-blog-category")
    public ResponseEntity<List<AllBlogResponseDto>> allBlogsPostByCategory(@RequestParam("category") String category){
        return ResponseEntity.ok(adminService.allBlogsByCat(category));
    }

    @GetMapping("/view-all-blog-creator")
    public ResponseEntity<List<AllBlogResponseDto>> allBlogsPostByCreator(@RequestParam("creator") String creator){
        return ResponseEntity.ok(adminService.allBlogsByCreator(creator));
    }

    @GetMapping("/all-comment-by-customer-per-blog")
    public ResponseEntity<List<?>> allCommentPerProduct(@RequestParam("blogId") Integer blogId){
        return ResponseEntity.ok(adminService.findAllByBlogId(blogId));

    }


    @PostMapping("/view-total-like-blog")
    public ResponseEntity<LikesCountResponseDto> blogLikeCount(@RequestBody LikeCountDto likeCountDto){
        return ResponseEntity.ok(adminService.getTotalCount(likeCountDto));
    }


}
