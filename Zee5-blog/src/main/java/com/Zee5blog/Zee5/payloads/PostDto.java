package com.Zee5blog.Zee5.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CatagoryDto category;
	private UserDto user;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public CatagoryDto getCategory() {
		return category;
	}
	public void setCategory(CatagoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public PostDto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	
	

	

}
