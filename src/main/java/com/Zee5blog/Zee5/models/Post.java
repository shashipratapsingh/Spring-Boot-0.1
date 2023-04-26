package com.Zee5blog.Zee5.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100)
	private String title;
	@Column(name = "content", length = 100)
	private String content;
	@Column(name = "imageName", length = 100)
	private String imageName;
	@Column(name = "addDate", length = 100)
	private Date addedDate;
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
	
	//commnets
	@OneToMany(mappedBy = "post" ,cascade=CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();

}
