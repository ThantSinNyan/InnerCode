package com.inner_code.service;


import com.inner_code.model.User;
import com.inner_code.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User ua = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user " + email));
        System.out.println("User found: " + ua);
        UserDetails userDetail=new org.springframework.security.core.userdetails.User(
                ua.getEmail(),
                ua.getPasswordHash(),
                List.of(new SimpleGrantedAuthority(ua.getRole())));
        System.out.println("UserDetail: " + userDetail);
        return userDetail;
    }
}
