package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.LikeCreateDto;
import com.example.fashionblog.dto.LikesCountResponseDto;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.entity.Like;
import com.example.fashionblog.enums.Rate;
import com.example.fashionblog.exceptions.BlogNotExist;
import com.example.fashionblog.repository.BlogRepository;
import com.example.fashionblog.repository.LikeRepository;
import com.example.fashionblog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final BlogRepository blogRepository;

    private final LikeRepository likeRepository;
    @Override
    public String create(LikeCreateDto likeCreateDto, Customer customer, Blog blog) {
        if(likeRepository.findByRateAndCustomerAndBlog(likeCreateDto.getRate(),customer,blog)!=null){
           if(likeCreateDto.getRate() == Rate.LIKE) return "You have already Liked this blog, You can choose to Dislike it";
           else return "You have already Disliked this blog, You can choose to Like it";
        }

        if(likeRepository.findByCustomerAndBlog(customer, blog) != null){
            Like like = likeRepository.findByCustomerAndBlog(customer,blog);
            like.setBlog(blog);
            like.setRate(likeCreateDto.getRate());
            like.setCustomer(customer);
            likeRepository.save(like);
            if(likeCreateDto.getRate() == Rate.LIKE) return "You Liked the blog!";
            return "You Disliked the blog";
        }

        Like like = Like.builder()
                .blog(blog)
                .rate(likeCreateDto.getRate())
                .customer(customer)
                .build();
        likeRepository.save(like);
        if(likeCreateDto.getRate() == Rate.LIKE) return "You just Liked the blog!";
        return "You just Disliked the blog";
    }

    @Override
    public LikesCountResponseDto getLikeCount(Rate rate, Integer blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(()-> new BlogNotExist("Blog does not exist!"));
        List<Like> rateCount = likeRepository.findAllByRateAndBlogId(rate,blogId);

        LikesCountResponseDto likeCount = LikesCountResponseDto.builder()
                .blogId(blogId)
                .blogPost(blog.getBlogPost())
                .categoryName(blog.getCategory().getCategoryName())
                .description(blog.getDescription())
                .imageUrl(blog.getImageUrl())
                .Total("The total "+ rate + " count for this blog is " + rateCount.size())
                .build();
        return likeCount ;
    }
}
