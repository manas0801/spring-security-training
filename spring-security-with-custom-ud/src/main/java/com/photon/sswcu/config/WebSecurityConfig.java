package com.photon.sswcu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser("user1").password("12345").authorities("READ")
//               .and().withUser("user2").password("12345").authorities("WRITE");

        auth.authenticationProvider(authenticationProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
       http.authorizeRequests().antMatchers("/api/v1/read").hasAuthority("READ")
       .antMatchers("/api/v1/write").hasAuthority("WRITE")
       .antMatchers("/api/v1/auth").authenticated()
       .anyRequest().permitAll();

    }
}
