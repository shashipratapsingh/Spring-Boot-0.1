package com.jwt.service;

import com.jwt.Dao.UserDoa;
import com.jwt.Util.JwtUtil;
import com.jwt.configuration.UserDetailsServiceImpl;
import com.jwt.entity.JwtRequest;
import com.jwt.entity.JwtResponse;
import com.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService
        //implements UserDetailsService
                {
    @Autowired
    private UserDoa userDoa;
    @Autowired
    private JwtUtil jwtUtil;

    private UserDetailsServiceImpl service;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String userName= jwtRequest.getUserName();
        String userPassword=jwtRequest.getUserPassword();
        authenticate(userName,userPassword);
      final UserDetails userDetails=service.loadUserByUsername(userName);
      String newGeneratedToken=jwtUtil.generateToken(userDetails);
     User user=userDoa.findById(userName).get();
     return new JwtResponse(user,newGeneratedToken);
    }
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

    private void authenticate(String userName, String userPassword) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (DisabledException e)
        {
            throw new Exception("user disable Exception");
        }catch (BadCredentialsException e)
        {
            throw new Exception("bad credentials form the user");
        }



    }
}
