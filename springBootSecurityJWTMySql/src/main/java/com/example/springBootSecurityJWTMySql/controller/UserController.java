package com.example.springBootSecurityJWTMySql.controller;

import com.example.springBootSecurityJWTMySql.entity.User;
import com.example.springBootSecurityJWTMySql.model.UserRequest;
import com.example.springBootSecurityJWTMySql.model.UserResponse;
import com.example.springBootSecurityJWTMySql.service.UserServiceImpl;
import com.example.springBootSecurityJWTMySql.util.JWTUtil;
import javafx.application.Application;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JWTUtil util;

    @Autowired
    private AuthenticationManager authenticationManager;

    //@Autowired
    //Principal principal;

    /**when you register first time **/
    @PostMapping(value = "/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
       Integer id = userService.saveUser(user);
       String str = "User "+id+ "saved";
       return ResponseEntity.status(HttpStatus.OK).body(str);
    }
    /**when you login ***/
    @PostMapping("/login")
    public  ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request){
        //TODO: validate username/password with database if credentials are valid then only generate the token
        //the below code validate the credentials , if valid jwt token will be generated , if not valid InvalidAuthEndPoint triggered
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        String token=util.generateToken(request.getUserName());
        return ResponseEntity.ok(new UserResponse(token,"success"));
    }
    @PostMapping("/welcome")
    public ResponseEntity<String>accessData(Principal principal){
        return ResponseEntity.ok("Hello User!"+principal.getName());
    }
}
