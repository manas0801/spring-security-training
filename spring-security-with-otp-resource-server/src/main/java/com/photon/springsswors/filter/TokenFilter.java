package com.photon.springsswors.filter;

import com.photon.springsswors.config.UserNameOtpAuthentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class TokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authentication");
        if(token.equals("abcdf")){
            UserNameOtpAuthentication userNameOtpAuthentication= new UserNameOtpAuthentication(token,null, Arrays.asList(new SimpleGrantedAuthority("USER"))) ;
            SecurityContextHolder.getContext().setAuthentication(userNameOtpAuthentication);

        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
