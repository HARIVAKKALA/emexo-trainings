package com.example.springBootSecurityJWTMySql.service;

import com.example.springBootSecurityJWTMySql.entity.User;
import com.example.springBootSecurityJWTMySql.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Integer saveUser(User user) {
        //Encode the password before the save
        String pwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(pwd);
        return userRepo.save(user).getId();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepo.findByUserName(username);
    }

    /**These methods are belongs to Spring security**/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = findByUserName(username);
        if (user.isPresent()) {
            throw new UsernameNotFoundException("User not exist");
        }
        User user1 = user.get();
        return new org.springframework.security.core.userdetails.User(username, user1.getPassword(), user1.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
    }
}
