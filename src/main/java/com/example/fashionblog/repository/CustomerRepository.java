package com.example.fashionblog.repository;

import com.example.fashionblog.entity.Admin;
import com.example.fashionblog.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Customer findByEmailAndRole(String email, String role);
}
