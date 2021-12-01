package com.photon.springsswors.proxy;

import com.photon.springsswors.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthProxy {

    @Value("${auth.url}")
    private String authUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void authUser(User user){

        restTemplate.postForEntity(authUrl+"/user/auth",user,Void.class);


    }

    public Boolean checkOtp(User user){

       ResponseEntity<Boolean> responseEntity=restTemplate.postForEntity(authUrl+"/otp/check",user,Boolean.class);
       return responseEntity.getStatusCode().is2xxSuccessful();
    }
}
