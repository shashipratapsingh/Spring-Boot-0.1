package com.Zee5blog.Zee5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Zee5blog.Zee5.exception.ResourceNotFountException;
import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.repostory.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(username)
				.orElseThrow(()->new ResourceNotFountException("user name", "email"+username));
	}

	
	
}
