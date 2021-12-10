package com.photon.ssor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtConfig {

//    @Value("${jwt.signing.key}")
//    String jwtSigningKey;

    @Value("${jwt.verifier.key}")
    String jwtVerifierKey;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){

        JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(jwtVerifierKey);
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


}
