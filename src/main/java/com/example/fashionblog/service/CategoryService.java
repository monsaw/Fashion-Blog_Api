package com.example.fashionblog.service;


import com.example.fashionblog.dto.CategoryCreateDto;
import com.example.fashionblog.dto.CategoryDeleteDto;

import com.example.fashionblog.entity.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    String createCategory(CategoryCreateDto categoryCreateDto);
    String deleteCategory(CategoryDeleteDto categoryDeleteDto);





}
