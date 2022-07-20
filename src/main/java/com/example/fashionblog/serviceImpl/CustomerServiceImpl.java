package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.CreateCommentDto;
import com.example.fashionblog.dto.CustomerCreateDto;
import com.example.fashionblog.dto.CustomerLoginDto;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.exceptions.AdminExistException;
import com.example.fashionblog.exceptions.AdminNotFound;
import com.example.fashionblog.exceptions.CustomerNotFoundException;
import com.example.fashionblog.repository.CustomerRepository;
import com.example.fashionblog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final CommentServiceImpl commentService;

    private final HttpSession httpSession;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CommentServiceImpl commentService, HttpSession httpSession) {
        this.customerRepository = customerRepository;
        this.commentService = commentService;
        this.httpSession = httpSession;
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
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Please Identify yourself!"));
        String Role = (String) httpSession.getAttribute("customerRole");

        if(Role.equals("") || Role == null){
            throw new CustomerNotFoundException("Please Sign in/Sign up as Customer");
        }
        if(!Role.equals("USER")){
            throw new CustomerNotFoundException("Please Sign in/Sign up first");
        }
        commentService.create(createCommentDto,customer);
    return customer.getName()+ " commented on this post "; }

    @Override
    public String logoutCustomer() {
        httpSession.invalidate();
        return "Customer logout successful!";
    }


}
