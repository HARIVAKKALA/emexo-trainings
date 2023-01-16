package com.example.springBootSecurityJWTMySql.service;

import com.example.springBootSecurityJWTMySql.entity.User;

import java.util.Optional;

public interface UserService {

    Integer saveUser(User user);
    Optional<User> findByUserName(String username);
}
