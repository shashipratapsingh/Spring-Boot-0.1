package com.flipkart.config;


import com.flipkart.Model.Authority;
import com.flipkart.Model.User;
import com.flipkart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email=authentication.getName();
        String password=authentication.getCredentials().toString();
        User user= this.userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found Exceptions"));
        if(passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(email,password,getAuthorities(user.getAuthorities()));
        }else {
            throw new BadCredentialsException("InValid credentials");
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<Authority> authorities) {
        Set<SimpleGrantedAuthority> list=new HashSet<>();
        for (Authority auth:authorities){
            list.add(new SimpleGrantedAuthority(auth.getAuthority()));
        }
        return list;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}