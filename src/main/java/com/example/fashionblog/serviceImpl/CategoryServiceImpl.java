package com.example.fashionblog.serviceImpl;


import com.example.fashionblog.dto.CategoryCreateDto;
import com.example.fashionblog.dto.CategoryDeleteDto;
import com.example.fashionblog.entity.Category;
import com.example.fashionblog.exceptions.CategoryExistException;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    final  CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String createCategory(CategoryCreateDto categoryCreateDto) {
        if(categoryRepository.existsByCategoryName(categoryCreateDto.getCategoryName().toUpperCase())){
           throw new CategoryExistException("Category Already Exist");
        }
        Category category = new Category();
        category.setCategoryName(categoryCreateDto.getCategoryName().toUpperCase());
        categoryRepository.save(category);
        return "Category Successfully created!";

    }

    @Override
    public String deleteCategory(CategoryDeleteDto categoryDeleteDto) {

            if(!categoryRepository.existsByCategoryName(categoryDeleteDto.getCategoryName().toUpperCase())){
                throw new CategoryExistException("Category not found , so you can't delete !");
            }
            Category category = new Category();
            category = categoryRepository.findByCategoryName(categoryDeleteDto.getCategoryName().toUpperCase());
            categoryRepository.delete(category);
        return "Category Successfully deleted!";
    }



}
