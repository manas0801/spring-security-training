package com.photon.springsswors.filter;

import com.photon.springsswors.config.UserNameOtpAuthentication;
import com.photon.springsswors.config.UserNamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class InitialAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${jwt.signing.key}")
    String jwtSigningKey;

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

          SecretKey jwtKey= Keys.hmacShaKeyFor(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
          Map<String,String> usermap= new HashMap<>();

          usermap.put("username",username);
          String jwtToken= Jwts.builder().setClaims(usermap).signWith(jwtKey).compact();

            response.addHeader("authentication",jwtToken);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
