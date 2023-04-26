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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min=3,message="Username must be abolve to3 charectors")
	private String name;
	@Email
	@Size(min=3,message="email must be abolve to3 charectors")
	private String email;

	@NotEmpty
	@Size(min=3,max=10,message="password must be min of 3  charectors max of 10")
	//@Pattern
	private String password;
	@NotEmpty
	private String about;

}
