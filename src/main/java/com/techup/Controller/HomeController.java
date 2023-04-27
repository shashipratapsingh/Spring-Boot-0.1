package com.techup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techup.Repository.RegistrationRepo;

@Controller
public class HomeController {
	
	@Autowired
	private RegistrationRepo registrationRepo;
	 
	@RequestMapping("/index")
	public String indexPage()
	{
		System.out.println("hey");
		return "index.html";
	}
	@RequestMapping("registration")
	public String registrationPage()
	{
		return "registration.html";
	}
	
	@RequestMapping("saveRegistration")
	public String saveRegistration()
	{
		
		return "registration.html";
	}
	
}
