package com.photon.springsswors.filter;

import com.photon.springsswors.config.UserNameOtpAuthentication;
import com.photon.springsswors.config.UserNamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class InitialAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username=request.getHeader("username");
        String password=request.getHeader("password");
        String otp=request.getHeader("otp");

        if(otp==null){
            UserNamePasswordAuthentication userNamePasswordAuthentication= new UserNamePasswordAuthentication(username,password);
            authenticationManager.authenticate(userNamePasswordAuthentication);
        }
        else{
            UserNameOtpAuthentication userNameOtpAuthentication= new UserNameOtpAuthentication(username,otp);
            authenticationManager.authenticate(userNameOtpAuthentication);

            //generate an token

            response.addHeader("authentication","abcdf");
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
