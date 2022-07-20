package com.example.fashionblog.serviceImpl;


import com.example.fashionblog.dto.AllBlogResponseDto;
import com.example.fashionblog.dto.BlogCreateDto;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Category;
import com.example.fashionblog.exceptions.BlogNotExist;
import com.example.fashionblog.exceptions.CategoryExistException;
import com.example.fashionblog.repository.BlogRepository;
import com.example.fashionblog.repository.CategoryRepository;
import com.example.fashionblog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    public final BlogRepository blogRepository;

    public final CategoryRepository categoryRepository;

    public BlogServiceImpl(BlogRepository blogRepository, CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;

    }


    @Override
    public String createBlog(BlogCreateDto blogCreateDto, Admin admin ) {
        Category category = categoryRepository.findByCategoryName(blogCreateDto.getCategoryName().toUpperCase());
        if (category == null) throw new CategoryExistException("No Category with the name above. Check current name");
        Blog blog = Blog.builder()
                .blogPost(blogCreateDto.getBlogPost())
                .category(category)
                .imageUrl(blogCreateDto.getImageUrl())
                .description(blogCreateDto.getDescription())
                .admin(admin)
                .build();
        blogRepository.save(blog);
    return "Blog Successfully created!!";}

    @Override
    public String deleteBlog(Integer blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(()-> new BlogNotExist("No such blog, " +
                "so deleting operation cannot happen.."));
        blogRepository.delete(blog);
        return "Blog Successfully deleted!";
    }

    @Override
    public List<AllBlogResponseDto> findAllBlog() {
        List<Blog> allBlog = blogRepository.findAll();
        List<AllBlogResponseDto> allBlogs = new ArrayList<>();
        for(Blog blog : allBlog){
            AllBlogResponseDto allBlogResponseDto = AllBlogResponseDto.builder()
                    .blogId(blog.getId())
                    .blogCreator(blog.getAdmin().getName())
                    .create(blog.getCreate())
                    .description(blog.getDescription())
                    .categoryName(blog.getCategory().getCategoryName())
                    .imageUrl(blog.getImageUrl())
                    .blogPost(blog.getBlogPost())
                    .build();
            allBlogs.add(allBlogResponseDto);
        }

        return allBlogs;
    }

    @Override
    public List<AllBlogResponseDto> findAllBlogByAdmin(String name) {

        List<Blog> allBlog = blogRepository.findAllByAdmin_NameOrderByCreateDesc(name);
        List<AllBlogResponseDto> allBlogs = new ArrayList<>();
        for(Blog blog : allBlog){
            AllBlogResponseDto allBlogResponseDto = AllBlogResponseDto.builder()
                    .blogId(blog.getId())
                    .blogCreator(blog.getAdmin().getName())
                    .create(blog.getCreate())
                    .description(blog.getDescription())
                    .categoryName(blog.getCategory().getCategoryName())
                    .imageUrl(blog.getImageUrl())
                    .blogPost(blog.getBlogPost())
                    .build();
            allBlogs.add(allBlogResponseDto);
        }

        return allBlogs;
    }

    @Override
    public List<AllBlogResponseDto> findAllBlogByCategory(String name) {
        List<Blog> allBlog = blogRepository.findAllByCategory_CategoryNameOrderByCreateDesc(name);
        List<AllBlogResponseDto> allBlogs = new ArrayList<>();
        for(Blog blog : allBlog){
            AllBlogResponseDto allBlogResponseDto = AllBlogResponseDto.builder()
                    .blogId(blog.getId())
                    .blogCreator(blog.getAdmin().getName())
                    .create(blog.getCreate())
                    .description(blog.getDescription())
                    .categoryName(blog.getCategory().getCategoryName())
                    .imageUrl(blog.getImageUrl())
                    .blogPost(blog.getBlogPost())
                    .build();
            allBlogs.add(allBlogResponseDto);
        }

        return allBlogs;
    }

}
