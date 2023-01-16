package com.example.springBootSecurityJWTMySql.filter;

import com.example.springBootSecurityJWTMySql.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil util;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
         //Read the token from Auth head

       String token= request.getHeader("Authorization");
   if(token!=null){
       //do validation
       String username=util.getUsername(token);
       //username must not be null and authentication must be null
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails user = userDetailsService.loadUserByUsername(username);
           //validate the token
       boolean isValid= util.validateToken(token,user.getUsername());
       if(isValid){
           UsernamePasswordAuthenticationToken authenticationToken
                   =new UsernamePasswordAuthenticationToken(username,user.getPassword());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource()
              .buildDetails(request));
      //final object stored security with user Details(un,password)
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       }
       }
   }filterChain.doFilter(request,response);
    }
}
