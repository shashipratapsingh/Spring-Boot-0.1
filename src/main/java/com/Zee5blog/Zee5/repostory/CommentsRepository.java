package com.Zee5blog.Zee5.repostory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Zee5blog.Zee5.models.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Integer>{
	
	 

}
