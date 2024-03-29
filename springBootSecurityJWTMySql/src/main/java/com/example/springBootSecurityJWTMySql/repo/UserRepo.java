package com.example.springBootSecurityJWTMySql.repo;

import com.example.springBootSecurityJWTMySql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
   Optional<User>findByUserName(String username);
}
