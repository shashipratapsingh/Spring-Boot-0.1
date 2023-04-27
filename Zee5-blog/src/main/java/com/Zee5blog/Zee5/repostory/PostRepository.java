package com.Zee5blog.Zee5.repostory;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.models.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public List<Post> findByUser(User user);

	public List<Post> findByCategory(Category category);

	public Page<Post> findAll(Pageable p);

	//List<Post> getAllPost(Integer pageNumber, Integer pageSize);

}