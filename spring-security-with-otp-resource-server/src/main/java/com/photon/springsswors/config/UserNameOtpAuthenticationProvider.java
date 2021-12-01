package com.photon.springsswors.config;

import com.photon.springsswors.model.User;
import com.photon.springsswors.proxy.AuthProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserNameOtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthProxy authProxy;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
           User user  = new User();
           user.setUsername(authentication.getName());
           user.setOtp(authentication.getCredentials().toString());
          if( authProxy.checkOtp(user)){

              return new UserNameOtpAuthentication(user.getUsername(),null);
          }
           else{
               throw new BadCredentialsException("Bad Credential Exception");
          }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UserNameOtpAuthentication.class);
    }
}
