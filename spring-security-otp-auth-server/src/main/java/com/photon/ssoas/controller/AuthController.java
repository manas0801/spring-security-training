package com.photon.ssoas.controller;

import com.photon.ssoas.model.User;
import com.photon.ssoas.model.UserOtp;
import com.photon.ssoas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity addUser(@RequestBody  User user){

        userService.addUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PostMapping("/user/auth")
    public  ResponseEntity authUser(@RequestBody User user){
        userService.authUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/otp/check")
    public ResponseEntity<Boolean> checkOtp(@RequestBody UserOtp userOtp){
       Boolean validOtpInd= userService.checkOtp(userOtp);
       return new ResponseEntity(validOtpInd,HttpStatus.OK);
    }
}
