package com.photon.springsswors.config;

import com.photon.springsswors.filter.InitialAuthFilter;
import com.photon.springsswors.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserNameOtpAuthenticationProvider userNameOtpAuthenticationProvider;
    @Autowired
    UserNamePasswordAuthProvider userNamePasswordAuthProvider;

    @Autowired
    InitialAuthFilter initialAuthFilter;

    @Autowired
    TokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(initialAuthFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(tokenFilter,BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userNameOtpAuthenticationProvider).authenticationProvider(userNamePasswordAuthProvider);
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
