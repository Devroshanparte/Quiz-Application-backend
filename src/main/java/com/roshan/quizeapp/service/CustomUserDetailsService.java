package com.roshan.quizeapp.service;

import com.roshan.quizeapp.model.AppUser;
import com.roshan.quizeapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        System.out.println("Loaded user: " + appUser.getUsername() + " / " + appUser.getUserPassword());

        return User.withUsername(appUser.getUsername())
                .password("{noop}" + appUser.getUserPassword()) // plain text password
                .roles(appUser.getRole().replace("ROLE_", "")) // ensure roles are formatted
                .build();
    }
}
