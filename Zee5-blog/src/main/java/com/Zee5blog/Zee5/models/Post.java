package com.Zee5blog.Zee5.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;
	@Column(name = "content", length = 100, nullable = false)
	private String content;
	@Column(name = "imageName")
	private String ImageName;
	@Column(name = "addedDate", length = 100, nullable = false)
	private Date addedDate;
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
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
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
	public Date getAddDate() {
		return addedDate;
	}
	public void setAddDate(Date addDate) {
		this.addedDate = addDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
