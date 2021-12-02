package com.photon.springsswors.filter;

import com.photon.springsswors.config.UserNameOtpAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Value("${jwt.signing.key}")
    String jwtSigningKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("authentication");
        SecretKey jwtKey= Keys.hmacShaKeyFor(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
        String username= Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(token).getBody().get("username",String.class);
        UserNameOtpAuthentication userNameOtpAuthentication= new UserNameOtpAuthentication(username,null, Arrays.asList(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(userNameOtpAuthentication);
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
