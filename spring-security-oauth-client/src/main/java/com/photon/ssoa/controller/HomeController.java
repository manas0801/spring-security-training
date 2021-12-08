package com.photon.ssoa.controller;

import com.photon.ssoa.model.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RequestMapping
@Controller
public class HomeController {
  @Value("${spring.security.oauth2.client.provider.myprovider.token-uri}")
  String tokenUri;

  @Value("${spring.security.oauth2.client.registration.myprovider.authorizationGrantType}")
  String grantType;

  @Value("${spring.security.oauth2.client.registration.myprovider.redirectUri}")
  String redirecturi;

  @GetMapping("/hello")
  public String helloController() {
    return "hello.html";
  }

  @GetMapping("/home")
  public String homeController(@RequestParam("code") String code,@RequestParam("state") String state, Model model) {

    // body
    // auth
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("code", code);
    map.add("grant_type", grantType);
    map.add("scope", "webclient");
    map.add("redirect_uri", redirecturi);
    String plainCred = "app2:sec2";
    String base64String=new String(Base64.getEncoder().encode(plainCred.getBytes()));
    System.out.println("auth " + base64String);
    httpHeaders.add(
        "Authorization", "basic " + base64String);
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, httpHeaders);
    AuthResponse authResponse =
        new RestTemplate().postForEntity(tokenUri, entity, AuthResponse.class).getBody();

    System.out.println("Token " + authResponse.getAccess_token());

    HttpHeaders httpHeaders1 = new HttpHeaders();
    httpHeaders.add(
        "authorization", authResponse.getToken_type() + " " + authResponse.getAccess_token());
    HttpEntity entity1 = new HttpEntity(httpHeaders);
    String message =
        new RestTemplate()
            .exchange("http://localhost:30008/api/v1/hello", HttpMethod.GET, entity1, String.class)
            .getBody();
    model.addAttribute("message", message);
    return "home.html";
  }
}
