package com.example.fashionblog.serviceImpl;

import com.example.fashionblog.dto.CustomerCreateDto;
import com.example.fashionblog.dto.CustomerLoginDto;
import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.entity.Customer;
import com.example.fashionblog.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {



    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    HttpSession httpSession;
    @Test
    void create() {
        CustomerCreateDto customerCreateDto = new CustomerCreateDto();
        customerCreateDto.setName("Paul");
        customerCreateDto.setPassword("12345");

        customerCreateDto.setEmail("Lawalmon@gmail.com");


        assertEquals("Customer Created!!", customerService.create(customerCreateDto) );

//        boolean createdAdmin = customerRepository.existsByEmail("Lawal@gmail.com");
//        assertTrue(createdAdmin);
//
//        Customer customer = customerRepository.findByEmail("Lawalmonsaw@gmail.com").get();
//        String role = "USER";
//        assertEquals(role, customer.getRole()) ;



    }

    @Test
    public void loginCustomerTest(){
        CustomerLoginDto customerLoginDto = new CustomerLoginDto();

        customerLoginDto.setEmail("Lawalmonsaw@gmail.com");
        customerLoginDto.setPassword("12345");
        customerService.loginCustomer(customerLoginDto);
        assertEquals("USER", httpSession.getAttribute("customerRole"));


    }

}