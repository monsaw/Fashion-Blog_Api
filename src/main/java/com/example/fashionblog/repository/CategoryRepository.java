package com.example.fashionblog.repository;

import com.example.fashionblog.dto.CategoryDeleteDto;
import com.example.fashionblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByCategoryName(String catName);

    void delete(Category category);

    Category findByCategoryName(String name);
}
