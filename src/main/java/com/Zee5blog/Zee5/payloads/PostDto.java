package com.Zee5blog.Zee5.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.Comment;
import com.Zee5blog.Zee5.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	
	private int postId;
	private String title;
	private String content;

	private String imageName;
	private Date addedDate;
	private CatagoryDto category;
	private UserDto user;
	
	
	private Set<CommentDto> comments=new HashSet<>();
	
	
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


	
}
