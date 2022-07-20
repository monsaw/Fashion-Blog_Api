package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.AdminCreateDto;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.exceptions.AdminExistException;
import com.example.fashionblog.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    CategoryServiceImpl categoryService;

    BlogServiceImpl blogService;

    AdminCreateDto adminCreateDto = new AdminCreateDto();
    @Autowired
    AdminServiceImpl adminService = new AdminServiceImpl(adminRepository, httpSession, categoryService, blogService);


    @Test
    void create() {
        adminCreateDto.setName("Paul");
        adminCreateDto.setPassword("12345");
        adminCreateDto.setEmail("Lawalmonsaw@gmail.com");
       // BeanUtils.copyProperties();
//        adminService.create(adminCreateDto);

        boolean createdAdmin = adminRepository.existsByEmail("Lawal@gmail.com");
        assertTrue(createdAdmin);

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
}