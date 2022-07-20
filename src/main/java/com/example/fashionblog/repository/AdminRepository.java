package com.example.fashionblog.repository;

import com.example.fashionblog.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    boolean existsByEmail(String email);

    Optional<Admin> findByEmail(String email);

    Admin findByEmailAndRole(String email, String role);

}
