package com.example.springBootSecurityJWTMySql.config;


import com.example.springBootSecurityJWTMySql.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private InvalidUserAuthEntryPoint invalidUserAuthEntryPoint;


    @Autowired
    private SecurityFilter securityFilter;


    @Override
    @Bean
    protected AuthenticationManager authenticationManager()throws Exception{
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
      auth.
              userDetailsService(userDetailsService)
              .passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception {
       /**for the first request token will be generated**/
        http.csrf().disable()
        .authorizeRequests()
                .antMatchers("/user/login", "/user/save","/v3/api-docs/**","/swagger-ui/**")
                .permitAll().anyRequest().authenticated()
                .and()/**if any exceptions below code run**/
                .exceptionHandling()
                .authenticationEntryPoint(invalidUserAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()//Register the filter 2nd request onwards...verify the user 2nd request onwards
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
