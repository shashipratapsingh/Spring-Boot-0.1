package com.Zee5blog.Zee5;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@SpringBootApplication
public class Zee5BlogApplication implements CommandLineRunner{


	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Zee5BlogApplication.class, args);

	}
	
	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("password Encodeed ->  "+this.passwordEncoder.encode("xyz"));
		//sassdssasesr@gmail.com
		// adsd
	}
	

	
	
	

}
