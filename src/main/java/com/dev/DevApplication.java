package com.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
/*
@ComponentScan({"@EnableJpaRepositories(\"com.dev.repository.CompanyRepo\")"})
@EntityScan("@EnableJpaRepositories(\"com.dev.repository.CompanyRepo\")")
@EnableJpaRepositories("@EnableJpaRepositories(\"com.dev.repository.CompanyRepo\")")
*/


public class DevApplication {
	public static void main(String[] args) {
		SpringApplication.run(DevApplication.class, args);



	}

	}
