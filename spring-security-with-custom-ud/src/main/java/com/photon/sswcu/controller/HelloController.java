package com.photon.sswcu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hello From Spring Security From "+authentication.getPrincipal().toString(), HttpStatus.OK);
    }
}
