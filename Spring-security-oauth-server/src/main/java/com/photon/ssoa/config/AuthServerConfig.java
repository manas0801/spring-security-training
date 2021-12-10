package com.photon.ssoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenStore jwtTokenStore;

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
//        clients.inMemory().withClient("app1").secret("sec1").authorizedGrantTypes("password","refresh_token").scopes("webclient")
//                .accessTokenValiditySeconds(300)
//                .and().withClient("app2").secret("sec2").authorizedGrantTypes("authorization_code","refresh_token").scopes("webclient")
//                .redirectUris("http://localhost:30005/home").accessTokenValiditySeconds(300)
//                .and().withClient("app3").secret("sec3").authorizedGrantTypes("client_credentials").scopes("webclient")
//                .and().withClient("app4").secret("sec4").authorizedGrantTypes("implicit").scopes("webclient").redirectUris("http://localhost:30006/home").accessTokenValiditySeconds(300)
//        .and().withClient("resource").secret("secret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
        .tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.checkTokenAccess("isAuthenticated()");
    }
}
