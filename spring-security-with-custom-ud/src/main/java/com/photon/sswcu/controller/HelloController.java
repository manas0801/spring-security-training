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

    @GetMapping("/read")
    public ResponseEntity<String> read(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hello From Spring Security-READ From "+authentication.getPrincipal().toString(), HttpStatus.OK);
    }
    @GetMapping("/write")
    public ResponseEntity<String> write(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hello From Spring Security-Write From "+authentication.getPrincipal().toString(), HttpStatus.OK);
    }
    @GetMapping("/auth")
    public ResponseEntity<String> auth(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hello From Spring Security-AUth From "+authentication.getPrincipal().toString(), HttpStatus.OK);
    }
}
