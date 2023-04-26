package com.Zee5blog.Zee5.repostory;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.models.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByUser(User user);

	public List<Post> findByCategory(Category category);

	public Page<Post> findAll(Pageable p);
	
	//@Query("select p from Post p where p.title like :key")
	//List<Post> serachByTitle(@Param("key")String title);
	
	List<Post> searchByTitleContaining(String title);
	
	
}
