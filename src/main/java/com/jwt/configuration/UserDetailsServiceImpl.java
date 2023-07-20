package com.jwt.configuration;

import com.jwt.Dao.UserDoa;
import com.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDoa userDoa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userDoa.findById(username).get();
        if (user !=null)
        {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthenticaties(user)
            );
        }else {
            throw new UsernameNotFoundException("username is not valid");
        }
    }

    private Set getAuthenticaties(User user){
        Set authorities=new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }
}
