package com.Zee5blog.Zee5.Impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Zee5blog.Zee5.exception.ResourceNotFoundException;
import com.Zee5blog.Zee5.models.Category;
import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.models.User;
import com.Zee5blog.Zee5.payloads.PostDto;
import com.Zee5blog.Zee5.payloads.PostResponse;
import com.Zee5blog.Zee5.repostory.CatagoryRepository;
import com.Zee5blog.Zee5.repostory.PostRepository;
import com.Zee5blog.Zee5.repostory.UserRepository;
import com.Zee5blog.Zee5.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CatagoryRepository catagoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		Category category = this.catagoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

		Post createdPost = this.modelMapper.map(postDto, Post.class);
		createdPost.setImageName("default.png");
		createdPost.setAddDate(new Date());
		createdPost.setUser(user);
		createdPost.setCategory(category);
		Post newPost = this.postRepository.save(createdPost);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	// delete post by id
	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepository.delete(post);
	}

	// finding all posts

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}

		Pageable p = (Pageable) PageRequest.of(pageNumber, pageSize,sort);

		Page<Post> pagePost = this.postRepository.findAll(p);

		List<Post> allPost = pagePost.getContent();

		List<PostDto> postDtos = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	// find by id

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category categories = this.catagoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> posts = this.postRepository.findByCategory(categories);
		return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		List<Post> posts = this.postRepository.findByUser(user);
		return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}