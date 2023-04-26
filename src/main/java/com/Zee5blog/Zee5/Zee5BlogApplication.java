package com.Zee5blog.Zee5;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class Zee5BlogApplication{


	public static void main(String[] args) {
		SpringApplication.run(Zee5BlogApplication.class, args);

	}
	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}

}
