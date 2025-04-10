package com.abdellah.hospitalapp.security.service;

import com.abdellah.hospitalapp.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private SecurityServiceImpl securityServiceimpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = securityServiceimpl.loadUserByUsername(username);
        // TO EXPLAIN
        String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        return User.withUsername(username)
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}
