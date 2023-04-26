package com.Zee5blog.Zee5.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.payloads.PostDto;
import com.Zee5blog.Zee5.payloads.PostResponse;

public interface PostService {

	// create

//	Post createPost(PostDto postDto);
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update

	PostDto updatePost(PostDto postDto, Integer postId);
	// delete

	public void deletePost(Integer postId);

	// get all list

	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
 
	// get all Single

	PostDto getPostById(Integer postId);

	// get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// get all post by user

	List<PostDto> getPostsByUser(Integer userId);

	// searching post

	//List<PostDto> searchPosts(String keyword);
	
	
	List<PostDto> searchByTitleContaining(String keywords);
	


	

}
