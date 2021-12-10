package com.photon.ssoa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.Key;
@Configuration
public class JwtConfig {

//    @Value("${jwt.signing.key}")
//    private String jwtSigningKey;
    @Value("${jks.location}")
    private String jksLocation;
    @Value("${jks.pass.key}")
    private String jksPasskey;
    @Value("${jks.alias}")
    private String jksAlias;


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter= new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory= new KeyStoreKeyFactory(new ClassPathResource(jksLocation),jksPasskey.toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(jksAlias));
        return jwtAccessTokenConverter;
    }
    @Bean
    public JwtTokenStore jwtTokenStore(){

        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
