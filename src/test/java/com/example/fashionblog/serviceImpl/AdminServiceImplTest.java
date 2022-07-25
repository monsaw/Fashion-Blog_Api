package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.*;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Category;
import com.example.fashionblog.exceptions.AdminExistException;
import com.example.fashionblog.exceptions.AdminNotFound;
import com.example.fashionblog.exceptions.CategoryExistException;
import com.example.fashionblog.repository.AdminRepository;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.service.AdminService;
import com.example.fashionblog.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;

import java.util.AbstractList;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import static org.mockito.Mockito.*;


@SpringBootTest
class AdminServiceImplTest {
    @InjectMocks
    AdminService adminService2 = mock(AdminServiceImpl.class);
    @Mock
    CategoryCreateDto categoryCreateDto;

    @Autowired
    AdminRepository adminRepository;


    @Autowired
    HttpSession httpSession;

    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    LikeServiceImpl likeService;

    AdminCreateDto adminCreateDto = new AdminCreateDto();
    AdminLoginDto adminLoginDto = new AdminLoginDto();
    @Autowired
    AdminServiceImpl adminService = new AdminServiceImpl(likeService, adminRepository,
            httpSession, categoryService,
            blogService, commentService);


    @Test
    void create() {
        adminCreateDto.setName("Paul");
        adminCreateDto.setPassword("12345");
        adminCreateDto.setEmail("Lawalmonsaw@gmail.com");
       // BeanUtils.copyProperties();
        adminService.create(adminCreateDto);

        boolean createdAdmin = adminRepository.existsByEmail("Lawal@gmail.com");
//        assertTrue(createdAdmin);

        Admin admin = adminRepository.findByEmail("Lawalmonsaw@gmail.com").get();
        String role = "ADMIN";
        assertEquals(role, admin.getRole()) ;
    }

    @Test
    public void AdminExistExceptionClass() {
        adminCreateDto.setName("Paul");
        adminCreateDto.setPassword("12345");
        adminCreateDto.setEmail("Lawalmonsaw@gmail.com");

        assertThrows(AdminExistException.class, () ->  adminService.create(adminCreateDto));
    }


    @Test
    public void loginAdminTest(){
        adminLoginDto.setEmail("Lawalmonsaw@gmail.com");
        adminLoginDto.setPassword("12345");
         adminService.loginAdmin(adminLoginDto);
        assertEquals("ADMIN", httpSession.getAttribute("adminRole"));


    }

    @Test
    public void logoutAdmin(){
        adminService.logoutAdmin();
        assertEquals(null, httpSession.getAttribute("adminRole"));

    }


    @Test
    public void adminCreateCategoryTest(){

        when(adminService2.getLoggedAdminByEmailAndRole()).thenReturn(true);

        categoryCreateDto = new CategoryCreateDto();
        categoryCreateDto.setCategoryName("Great");
//
//        assertThrows(CategoryExistException.class, ()-> categoryService.createCategory(categoryCreateDto));

        assertEquals("Category Successfully created!", categoryService.createCategory(categoryCreateDto));

    }

    @Test void adminCreateBlogTest(){

        CategoryRepository categoryRepository = mock(CategoryRepository.class);

        BlogCreateDto blogCreateDto = new BlogCreateDto();
        blogCreateDto.setBlogPost("Messi Leaving barca");
        blogCreateDto.setCategoryName("Greater");
        blogCreateDto.setDescription("Messi was treated bad while his staying in barcelona");
        blogCreateDto.setImageUrl("www.messi.com");

        Category category = new Category();
       when(categoryRepository.findByCategoryName(blogCreateDto.getCategoryName().toUpperCase())).thenReturn(category);
        Admin admin = new Admin();
        admin.setRole("");
        admin.setEmail("lawalmonsaw@gmail.com");
        admin.setName("Lawal");

//        assertThrows(CategoryExistException.class, ()->blogService.createBlog(blogCreateDto, admin ));
    }




}