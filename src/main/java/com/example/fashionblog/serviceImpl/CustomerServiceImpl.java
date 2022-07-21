package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.dto.CustomerCreateDto;
import com.example.fashionblog.dto.CustomerLoginDto;
import com.example.fashionblog.dto.LikeCreateDto;
import com.example.fashionblog.entity.Blog;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.exceptions.AdminExistException;
import com.example.fashionblog.exceptions.AdminNotFound;
import com.example.fashionblog.exceptions.BlogNotExist;
import com.example.fashionblog.exceptions.CustomerNotFoundException;
import com.example.fashionblog.repository.BlogRepository;
import com.example.fashionblog.repository.CustomerRepository;
import com.example.fashionblog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CommentServiceImpl commentService;

    private final BlogRepository blogRepository;

    private final HttpSession httpSession;

    private final LikeServiceImpl likeService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CommentServiceImpl commentService, BlogRepository blogRepository, HttpSession httpSession, LikeServiceImpl likeService) {
        this.customerRepository = customerRepository;
        this.commentService = commentService;
        this.blogRepository = blogRepository;
        this.httpSession = httpSession;
        this.likeService = likeService;
    }

    @Override
    public String create(CustomerCreateDto customerCreateDto) {
        boolean isExist = customerRepository.existsByEmail(customerCreateDto.getEmail());
        if(isExist){
            throw new AdminExistException("Customer with " + customerCreateDto.getEmail() + " already exist!!");
        }
        Customer customer = Customer.builder()
                .name(customerCreateDto.getName())
                .email(customerCreateDto.getEmail())
                .password(customerCreateDto.getPassword())
                .role("USER")
                .build();
        customerRepository.save(customer);
        return "Customer Created!!";


    }

    @Override
    public String loginCustomer(CustomerLoginDto customerLoginDto) {

        String email = customerLoginDto.getEmail();
        Customer currentCustomer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new AdminNotFound("Email or Password is not correct!"));

        if(!customerLoginDto.getPassword().equals(currentCustomer.getPassword())){
            throw new AdminNotFound("Email or Password is not correct!");
        }

//        httpSession.setAttribute("adminEmail", currentCustomer.getEmail());
        httpSession.setAttribute("customerRole", currentCustomer.getRole());
//        httpSession.setAttribute("adminName", currentCustomer.getName());
        httpSession.setAttribute("id" , currentCustomer.getId());

        return "Customer login successful!!";




    }

    @Override
    public String createComment(CreateCommentDto createCommentDto) {

        Integer customerId = (Integer) httpSession.getAttribute("id");
        if (customerId == null) throw new CustomerNotFoundException("Login please");
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Please Identify yourself as Customer!"));
        String Role = (String) httpSession.getAttribute("customerRole");

        if(Role == null){
            throw new CustomerNotFoundException("Please Sign in/Sign up as Customer");
        }
        if(!Role.equals("USER")){
            throw new CustomerNotFoundException("Please Sign in/Sign up first");
        }
        commentService.create(createCommentDto,customer);
    return customer.getName()+ " commented on this blog";}

    @Override
    public String createLike(LikeCreateDto likeCreateDto) {
        Integer customerId = (Integer) httpSession.getAttribute("id");
        if (customerId == null) throw new CustomerNotFoundException("Login please");
        Blog blog = blogRepository.findById(likeCreateDto.getBlogId()).orElseThrow(()-> new BlogNotExist("Blog does not exist!"));
        Customer customer = customerRepository.findById(
                customerId).orElseThrow(()-> new CustomerNotFoundException("Please Identify yourself as Customer!"));
        if(!customer.getRole().equals("USER")) throw new CustomerNotFoundException("You must be a Customer to Like");
        return likeService.create(likeCreateDto, customer, blog);
    }

    @Override
    public String logoutCustomer() {
        httpSession.invalidate();
        return "Customer logout successful!";
    }


}
