package com.photon.springsswors.config;

import com.photon.springsswors.model.User;
import com.photon.springsswors.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserNamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    AuthProxy authProxy;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user  = new User();
        user.setUsername(authentication.getName());
        user.setPassword(authentication.getCredentials().toString());
        authProxy.authUser(user);
        return new UserNamePasswordAuthentication(user.getUsername(),null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UserNamePasswordAuthentication.class);
    }
}
