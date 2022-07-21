package com.example.fashionblog.serviceImpl;


import com.example.fashionblog.dto.*;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.enums.Rate;
import com.example.fashionblog.exceptions.AdminExistException;
import com.example.fashionblog.exceptions.AdminNotFound;
import com.example.fashionblog.repository.AdminRepository;
import com.example.fashionblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final LikeServiceImpl likeService;

    private final AdminRepository adminRepository;
    private final HttpSession httpSession;

    private final CategoryServiceImpl categoryService;

    private final BlogServiceImpl blogService;

    private final CommentServiceImpl commentService;

    @Autowired
    public AdminServiceImpl(LikeServiceImpl likeService, AdminRepository adminRepository, HttpSession httpSession,
                            CategoryServiceImpl
            categoryService, BlogServiceImpl blogService, CommentServiceImpl commentService) {
        this.likeService = likeService;
        this.adminRepository = adminRepository;
        this.httpSession = httpSession;
        this.categoryService = categoryService;
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @Override
    public String create(AdminCreateDto adminCreateDto) {
        boolean isExist = adminRepository.existsByEmail(adminCreateDto.getEmail());
        if(isExist){
            throw new AdminExistException("Admin with " + adminCreateDto.getEmail() + " already exist!!");
        }
        Admin admin = Admin.builder()
                .name(adminCreateDto.getName())
                .email(adminCreateDto.getEmail())
                .password(adminCreateDto.getPassword())
                .role("ADMIN")
                .build();
        adminRepository.save(admin);
        return "Admin Created!!";
    }

    @Override
    public String loginAdmin(AdminLoginDto adminLoginDto) {
            String email = adminLoginDto.getEmail();
            Admin currentAdmin = adminRepository.findByEmail(email)
                    .orElseThrow(()-> new AdminNotFound("Email or Password is not correct!"));

            if(!adminLoginDto.getPassword().equals(currentAdmin.getPassword())){
                throw new AdminNotFound("Email or Password is not correct!");
            }

            httpSession.setAttribute("adminEmail", currentAdmin.getEmail());
            httpSession.setAttribute("adminRole", currentAdmin.getRole());
            httpSession.setAttribute("adminName", currentAdmin.getName());
            httpSession.setAttribute("id" , currentAdmin.getId());

        return " Admin login successful!!";
    }

    @Override
    public String logoutAdmin() {
        httpSession.invalidate();
        return "Logout successful!";
    }

    @Override
    public String createCategory(CategoryCreateDto categoryCreateDto) {
        if(getLoggedAdminByEmailAndRole()){
        categoryService.createCategory(categoryCreateDto);
        return "Successfully created!!!";
        }

    return "Not Successful..";}

    @Override
    public String deleteCategory(CategoryDeleteDto categoryDeleteDto) {
        if(getLoggedAdminByEmailAndRole()){
            categoryService.deleteCategory(categoryDeleteDto);
            return "Successfully Deleted!!!";}
        return "Not Successful..";
    }

    @Override
    public String createBlog(BlogCreateDto blogCreateDto) {
        if(getLoggedAdminByEmailAndRole()){
            String adminEmail =  (String) httpSession.getAttribute("adminEmail");
            String Role = (String) httpSession.getAttribute("adminRole");
            Admin admin = adminRepository.findByEmailAndRole(adminEmail,Role);
            blogService.createBlog(blogCreateDto,admin);
            return "Blog Successfully created!!!";
        }

        return "Not Successful..";

    }

    @Override
    public String deleteBlog(Integer blogId) {
        if(getLoggedAdminByEmailAndRole()){
            blogService.deleteBlog(blogId);
            return "Successfully Deleted!!!";}
        return "Not Successful..";
    }

    @Override
    public List<AllBlogResponseDto> allBlogs() {
        Integer verify = (Integer) httpSession.getAttribute("id");
        if (verify != null){
            return blogService.findAllBlog();
        }
        throw new AdminNotFound("You must sign in to see all Blogs!");
    }

    @Override
    public List<AllBlogResponseDto> allBlogsByCat(String name) {
        Integer verify = (Integer) httpSession.getAttribute("id");
        if (verify != null){
            List<AllBlogResponseDto> blogs = blogService.findAllBlogByCategory(name);
            if(blogs.size() < 1) throw new AdminNotFound("No Blog is Related to this category name! ");
            return blogService.findAllBlogByCategory(name);
        }
        throw new AdminNotFound("You must sign in to see all Blogs by Category!");
    }


    @Override
    public List<AllBlogResponseDto> allBlogsByCreator(String name) {
        Integer verify = (Integer) httpSession.getAttribute("id");
        if (verify != null){
            List<AllBlogResponseDto> blogs = blogService.findAllBlogByAdmin(name);
            if(blogs.size() < 1) throw new AdminNotFound("No Blog is Created by this name! ");

            return blogService.findAllBlogByAdmin(name);
        }
        throw new AdminNotFound("You must sign in to see all Blogs by Creator!");

    }

    @Override
    public List<?> findAllByBlogId(Integer blogId) {
        if(getLoggedAdminByEmailAndRole()){
            return commentService.findAllByBlogId(blogId);
        }
        throw new AdminNotFound("You are required to login as admin to view all comment...");

    }

    @Override
    public LikesCountResponseDto getTotalCount(LikeCountDto likeCountDto) {
        if(getLoggedAdminByEmailAndRole()){
            return likeService.getLikeCount(likeCountDto.getRate(),likeCountDto.getBlogId());
        }
        throw new AdminNotFound("You are required to login as admin to view all like and dislike blog...");

    }


    private Boolean getLoggedAdminByEmailAndRole() {
        String adminEmail =  (String) httpSession.getAttribute("adminEmail");
        String Role = (String) httpSession.getAttribute("adminRole");

        if(adminEmail == null || Role == null) throw new AdminNotFound("Please log as Admin to perform this operation!");
        Admin admin = adminRepository.findByEmailAndRole(adminEmail,Role);

            if(admin == null){
                throw new AdminNotFound("You don't have authority to perform this operation");
            }

        return true;
    }



}
