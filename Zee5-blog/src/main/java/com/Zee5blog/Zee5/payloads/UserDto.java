package com.Zee5blog.Zee5.payloads;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.Zee5blog.Zee5.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min=3,message="Username must be abolve to 3 charectors")
	private String name;
	@Email
	@Size(min=3,message="email must be abolve to 3  charectors")
	private String email;

	@NotEmpty
	@Size(min=3,max=10,message="password must be min of 3  charectors max of 10")
	//@Pattern
	private String password;
	@NotEmpty
	private String about;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	

}
