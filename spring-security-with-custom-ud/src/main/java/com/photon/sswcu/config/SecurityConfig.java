package com.photon.sswcu.config;

import com.photon.sswcu.model.SecurityUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {


//    @Bean
//    public UserDetailsService userDetailsService(){
//        SecurityUser securityUser1= new SecurityUser("user1",passwordEncoder().encode("12345"), "READ");
//        SecurityUser securityUser2= new SecurityUser("user2",passwordEncoder().encode("12345"),"READ");
//
//        List<? extends UserDetails> list=  Arrays.asList(securityUser1,securityUser2);
//
//        CustomUserDetailsService customUserDetailsService= new CustomUserDetailsService(list);
//
//        return customUserDetailsService;
//
//
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
      return   NoOpPasswordEncoder.getInstance();

    }






}
