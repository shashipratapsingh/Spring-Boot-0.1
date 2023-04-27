package com.techup.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="queryTable")
public class RegistrationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String phonenumber;
	private String company;
	private String type;
	private String message;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "RegistrationEntity [name=" + name + ", email=" + email + ", phonenumber=" + phonenumber + ", company="
				+ company + ", type=" + type + ", message=" + message + "]";
	}
	public RegistrationEntity(String name, String email, String phonenumber, String company, String type,
			String message) {
		super();
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
		this.company = company;
		this.type = type;
		this.message = message;
	}
	
	
}
