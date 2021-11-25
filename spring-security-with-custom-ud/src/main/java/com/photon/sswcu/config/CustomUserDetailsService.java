package com.photon.sswcu.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    private List<? extends UserDetails> userStore;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return userStore.stream()
                .filter(user->user.getUsername().equals(username)).findFirst().get();
    }

    public List<? extends UserDetails> getUserStore() {
        return userStore;
    }

    public void setUserStore(List<? extends UserDetails> userStore) {
        this.userStore = userStore;
    }

    public CustomUserDetailsService(List<? extends UserDetails> userStore) {
        this.userStore = userStore;
    }
}
