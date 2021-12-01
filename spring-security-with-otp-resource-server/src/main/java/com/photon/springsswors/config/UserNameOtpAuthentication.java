package com.photon.springsswors.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserNameOtpAuthentication extends UserNamePasswordAuthentication {
    public UserNameOtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UserNameOtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
