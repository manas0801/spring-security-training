package com.photon.ssoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("app1").secret("sec1").authorizedGrantTypes("password").scopes("webclient")
                .accessTokenValiditySeconds(300)
        .and().withClient("resource").secret("secret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
       security.checkTokenAccess("isAuthenticated()");
    }
}
